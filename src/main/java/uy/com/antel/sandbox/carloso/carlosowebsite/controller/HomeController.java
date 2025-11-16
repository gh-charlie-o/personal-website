package uy.com.antel.sandbox.carloso.carlosowebsite.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uy.com.antel.sandbox.carloso.carlosowebsite.model.ContentCreatorDTO;
import uy.com.antel.sandbox.carloso.carlosowebsite.services.ContentCreatorService;
import uy.com.antel.sandbox.carloso.carlosowebsite.services.PostService;
import uy.com.antel.sandbox.carloso.carlosowebsite.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${domain.registration.date}")
    private String registrationDateString;

    private final ContentCreatorService contentCreatorService;
    private final PostService postService;

    public HomeController(final ContentCreatorService contentCreatorService, final PostService postService) {
        this.contentCreatorService = contentCreatorService;
        this.postService = postService;
    }

    @GetMapping({"/", "/home", "/index"})
    public String home(final Model model) {
        ZonedDateTime registrationDate = ZonedDateTime.of(
                2016, Month.AUGUST.getValue(), 16, 8, 0, 14, 0, ZoneOffset.ofHours(-3));

        model.addAttribute("timeSinceRegistration", registrationDate);
        //model.addAttribute("timeSinceRegistration", calculateTimeSinceRegistration());
        return "index";
    }

    @GetMapping("/time-since-registration")
    @ResponseBody
    public String getTimeSinceRegistration() {
        return calculateTimeSinceRegistration();
    }

    private String calculateTimeSinceRegistration() {
        LocalDateTime registrationDate = LocalDateTime.parse(registrationDateString, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        LocalDateTime now = LocalDateTime.now();

        long totalSeconds = ChronoUnit.SECONDS.between(registrationDate, now);
        
        long years = totalSeconds / (365 * 24 * 3600);
        totalSeconds %= (365 * 24 * 3600);
        
        long months = totalSeconds / (30 * 24 * 3600);
        totalSeconds %= (30 * 24 * 3600);
        
        long days = totalSeconds / (24 * 3600);
        totalSeconds %= (24 * 3600);
        
        long hours = totalSeconds / 3600;
        totalSeconds %= 3600;
        
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        return String.format("%d años, %d meses, %d días, %d horas, %d minutos y %d segundos",
                years, months, days, hours, minutes, seconds);
    }

    @GetMapping("/category/{category}")
    public String categoryPage(@PathVariable String category, Model model) {
        // Category metadata
        Map<String, Map<String, String>> categoryInfo = getCategoryInfo();
        
        if (!categoryInfo.containsKey(category)) {
            return "redirect:/";
        }
        
        Map<String, String> info = categoryInfo.get(category);
        model.addAttribute("categorySlug", category);
        model.addAttribute("categoryTitle", info.get("title"));
        model.addAttribute("categoryDescription", info.get("description"));
        
        // Get initial posts (page 0) from DB
        Page<Post> page0 = postService.getPublishedByCategory(category, PageRequest.of(0, 10));
        List<Map<String, Object>> posts = mapPostsForView(page0.getContent(), category);
        model.addAttribute("posts", posts);
        model.addAttribute("hasMore", page0.hasNext());
        model.addAttribute("nextPage", 1);
        
        // Get recommended content creators for this category
        List<ContentCreatorDTO> creators = getCreatorsForCategory(category);
        model.addAttribute("creators", creators);
        
        return "category";
    }

    @GetMapping("/category/{category}/posts")
    public String categoryPosts(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        
        Page<Post> postPage = postService.getPublishedByCategory(category, PageRequest.of(page, 10));
        List<Map<String, Object>> posts = mapPostsForView(postPage.getContent(), category);
        
        model.addAttribute("posts", posts);
        model.addAttribute("categorySlug", category);
        model.addAttribute("hasMore", postPage.hasNext());
        model.addAttribute("nextPage", page + 1);
        
        return "fragments/posts :: posts-htmx";
    }

    @GetMapping("/post/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        return postService.getPublishedById(id)
                .map(post -> {
                    String categorySlug = post.getCategory();
                    Map<String, Map<String, String>> info = getCategoryInfo();
                    String categoryTitle = info.getOrDefault(categorySlug, Map.of("title", categorySlug)).get("title");

                    model.addAttribute("title", post.getTitle());
                    model.addAttribute("categoryTitle", categoryTitle);
                    model.addAttribute("categorySlug", categorySlug);
                    model.addAttribute("date", post.getPublishedAt());
                    model.addAttribute("readTime", (post.getReadTimeMinutes() != null ? post.getReadTimeMinutes() : 5) + " min de lectura");
                    model.addAttribute("author", post.getAuthor());
                    model.addAttribute("tags", post.getTags());
                    model.addAttribute("coverImageUrl", post.getCoverImageUrl());
                    model.addAttribute("content", post.getContent());

                    return "post";
                })
                .orElse("redirect:/");
    }

    private List<Map<String, Object>> mapPostsForView(List<Post> postList, String categorySlug) {
        List<Map<String, Object>> posts = new ArrayList<>();
        String categoryTitle = getCategoryInfo().getOrDefault(categorySlug, Map.of("title", categorySlug)).get("title");
        for (Post p : postList) {
            Map<String, Object> m = new HashMap<>();
            m.put("id", p.getId());
            m.put("title", p.getTitle());
            m.put("category", categoryTitle);
            m.put("date", p.getPublishedAt() != null ? p.getPublishedAt() : LocalDate.now());
            int rt = p.getReadTimeMinutes() != null ? p.getReadTimeMinutes() : 5;
            m.put("readTime", rt + " min de lectura");
            m.put("author", p.getAuthor());
            m.put("excerpt", p.getExcerpt());
            m.put("tags", p.getTags());
            posts.add(m);
        }
        return posts;
    }

    // Helper method to get category information
    private Map<String, Map<String, String>> getCategoryInfo() {
        Map<String, Map<String, String>> categories = new HashMap<>();
        
        Map<String, String> nutrition = new HashMap<>();
        nutrition.put("title", "Nutrición & Salud");
        nutrition.put("description", "Explorá recursos basados en evidencia sobre nutrición, dieta y hábitos de alimentación saludable. Aprendé sobre la ciencia detrás de la nutrición y cómo tomar decisiones informadas sobre lo que comés (aunque los bizcochos siempre tientan).");
        categories.put("nutrition", nutrition);
        
        Map<String, String> crossfit = new HashMap<>();
        crossfit.put("title", "CrossFit");
        crossfit.put("description", "Descubrí programas de entrenamiento, consejos para los WODs y eso que llaman 'comunidad'. Mejorá tu rendimiento en CrossFit con recursos curados y consejos de expertos (que en realidad son tan mortales como vos).");
        categories.put("crossfit", crossfit);
        
        Map<String, String> growth = new HashMap<>();
        growth.put("title", "Crecimiento Personal");
        growth.put("description", "Mejorá tu mentalidad, productividad y desarrollo personal. Accedé a recursos sobre mindfulness (o como le decimos acá, 'estar presente'), manejo del tiempo y establecimiento de metas.");
        categories.put("growth", growth);
        
        Map<String, String> springboot = new HashMap<>();
        springboot.put("title", "Spring Boot & IA");
        springboot.put("description", "Explorá Spring Boot, IA y otras nerdadas de desarrollo de software. Mantenete al día con las últimas tendencias y mejores prácticas en ingeniería de software (porque cada semana sale un framework nuevo, dale nomás).");
        categories.put("springboot", springboot);
        
        Map<String, String> crypto = new HashMap<>();
        crypto.put("title", "Cripto & Finanzas");
        crypto.put("description", "Aprendé sobre criptomonedas, finanzas y estrategias de inversión. Obtené información sobre mercados financieros y oportunidades de inversión (no, no es consejo financiero, consulta a un profesional bo).");
        categories.put("crypto", crypto);
        
        return categories;
    }

    // Helper method to generate sample posts
    private List<Map<String, Object>> getPostsForCategory(String category, int page) {
        List<Map<String, Object>> posts = new ArrayList<>();
        
        // Generate 10 sample posts per page
        int postsPerPage = 10;
        int startId = page * postsPerPage + 1;
        
        for (int i = 0; i < postsPerPage; i++) {
            Map<String, Object> post = new HashMap<>();
            int postId = startId + i;
            
            post.put("id", postId);
            post.put("title", getCategoryPostTitle(category, postId));
            post.put("category", getCategoryInfo().get(category).get("title"));
            post.put("date", LocalDate.now().minusDays(postId));
            post.put("readTime", (5 + (postId % 10)) + " min de lectura");
            post.put("author", "Carlos Olivera");
            post.put("excerpt", getCategoryPostExcerpt(category, postId));
            post.put("tags", Arrays.asList(
                category.substring(0, 1).toUpperCase() + category.substring(1),
                "Artículo",
                "Tutorial"
            ));
            
            posts.add(post);
        }
        
        return posts;
    }

    // Helper method to generate post titles based on category
    private String getCategoryPostTitle(String category, int id) {
        switch (category) {
            case "nutrition":
                return "Entendiendo los Macronutrientes: Una Guía Completa #" + id;
            case "crossfit":
                return "Técnicas de Entrenamiento CrossFit y Mejores Prácticas #" + id;
            case "growth":
                return "Desarrollo Personal: Construyendo Mejores Hábitos #" + id;
            case "springboot":
                return "Spring Boot & IA: Enfoques Modernos de Desarrollo #" + id;
            case "crypto":
                return "Cripto Finanzas: Entendiendo los Fundamentos #" + id;
            default:
                return "Artículo #" + id;
        }
    }

    // Helper method to generate post excerpts
    private String getCategoryPostExcerpt(String category, int id) {
        switch (category) {
            case "nutrition":
                return "Explorá la ciencia detrás de los macronutrientes y aprendé cómo optimizar tu dieta para mejor salud y rendimiento. Esta guía completa cubre proteínas, carbohidratos y grasas (spoiler: todos son importantes, dejá de demonizar ninguno).";
            case "crossfit":
                return "Descubrí técnicas efectivas de entrenamiento CrossFit que te ayudarán a mejorar tu rendimiento, prevenir lesiones y alcanzar tus objetivos fitness a través de enfoques basados en evidencia (y no en lo que dice el coach que se cree gurú).";
            case "growth":
                return "Aprendé estrategias prácticas para el desarrollo personal y la formación de hábitos. Este artículo explora métodos comprobados para construir sistemas sostenibles que llevan al éxito a largo plazo (nada de soluciones mágicas acá).";
            case "springboot":
                return "Metete a fondo en el desarrollo Spring Boot y la integración de IA. Aprendé sobre arquitectura de microservicios, mejores prácticas y cómo construir aplicaciones escalables (que no se caigan cuando tenés más de 3 usuarios).";
            case "crypto":
                return "Entendé los fundamentos de las criptomonedas y la tecnología blockchain. Aprendé sobre estrategias de inversión, gestión de riesgos y el futuro de las finanzas digitales (pero ojo, que esto no te convierte en Warren Buffett).";
            default:
                return "Un artículo interesante sobre " + category + ". Leé más para descubrir información valiosa y consejos prácticos.";
        }
    }

    private List<ContentCreatorDTO> getCreatorsForCategory(String category) {
        return this.contentCreatorService.getCreatorsForCategory(category);
    }
}