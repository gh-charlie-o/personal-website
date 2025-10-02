package uy.com.antel.sandbox.carloso.carlosowebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping({"/", "/home", "/index"})
    public String home() {
        return "index";
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
        
        // Get initial posts (page 0)
        List<Map<String, Object>> posts = getPostsForCategory(category, 0);
        model.addAttribute("posts", posts);
        model.addAttribute("hasMore", posts.size() >= 10);
        model.addAttribute("nextPage", 1);
        
        return "category";
    }

    @GetMapping("/category/{category}/posts")
    public String categoryPosts(
            @PathVariable String category,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        
        // Get posts for the requested page
        List<Map<String, Object>> posts = getPostsForCategory(category, page);
        
        model.addAttribute("posts", posts);
        model.addAttribute("categorySlug", category);
        model.addAttribute("hasMore", posts.size() >= 10 && page < 3); // Limit to 4 pages for demo
        model.addAttribute("nextPage", page + 1);
        
        return "fragments/posts :: posts-htmx";
    }

    // Helper method to get category information
    private Map<String, Map<String, String>> getCategoryInfo() {
        Map<String, Map<String, String>> categories = new HashMap<>();
        
        Map<String, String> nutrition = new HashMap<>();
        nutrition.put("title", "Nutrition & Health");
        nutrition.put("description", "Explore evidence-based resources on nutrition, diet, and healthy eating habits. Learn about the science behind nutrition and how to make informed dietary choices.");
        categories.put("nutrition", nutrition);
        
        Map<String, String> crossfit = new HashMap<>();
        crossfit.put("title", "CrossFit");
        crossfit.put("description", "Discover training programs, workout tips, and community insights. Enhance your CrossFit performance with curated resources and expert advice.");
        categories.put("crossfit", crossfit);
        
        Map<String, String> growth = new HashMap<>();
        growth.put("title", "Personal Growth");
        growth.put("description", "Improve your mindset, productivity, and personal development. Access resources on mindfulness, time management, and goal setting.");
        categories.put("growth", growth);
        
        Map<String, String> springboot = new HashMap<>();
        springboot.put("title", "Spring Boot & AI");
        springboot.put("description", "Explore Spring Boot, AI, and other software development topics. Stay updated with the latest trends and best practices in software engineering.");
        categories.put("springboot", springboot);
        
        Map<String, String> crypto = new HashMap<>();
        crypto.put("title", "Crypto & Finance");
        crypto.put("description", "Learn about cryptocurrency, finance, and investment strategies. Gain insights into financial markets and investment opportunities.");
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
            post.put("readTime", (5 + (postId % 10)) + " min read");
            post.put("author", "Carlos Olivera");
            post.put("excerpt", getCategoryPostExcerpt(category, postId));
            post.put("tags", Arrays.asList(
                category.substring(0, 1).toUpperCase() + category.substring(1),
                "Article",
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
                return "Understanding Macronutrients: A Complete Guide #" + id;
            case "crossfit":
                return "CrossFit Training Techniques and Best Practices #" + id;
            case "growth":
                return "Personal Development: Building Better Habits #" + id;
            case "springboot":
                return "Spring Boot & AI: Modern Development Approaches #" + id;
            case "crypto":
                return "Crypto Finance: Understanding the Fundamentals #" + id;
            default:
                return "Article #" + id;
        }
    }

    // Helper method to generate post excerpts
    private String getCategoryPostExcerpt(String category, int id) {
        switch (category) {
            case "nutrition":
                return "Explore the science behind macronutrients and learn how to optimize your diet for better health and performance. This comprehensive guide covers proteins, carbohydrates, and fats.";
            case "crossfit":
                return "Discover effective CrossFit training techniques that will help you improve your performance, prevent injuries, and achieve your fitness goals through evidence-based approaches.";
            case "growth":
                return "Learn practical strategies for personal development and habit formation. This article explores proven methods for building sustainable systems that lead to long-term success.";
            case "springboot":
                return "Deep dive into Spring Boot development and AI integration. Learn about microservices architecture, best practices, and how to build scalable applications.";
            case "crypto":
                return "Understand the fundamentals of cryptocurrency and blockchain technology. Learn about investment strategies, risk management, and the future of digital finance.";
            default:
                return "An insightful article about " + category + ". Read more to discover valuable information and practical tips.";
        }
    }
}