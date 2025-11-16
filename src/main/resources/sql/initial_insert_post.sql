INSERT INTO posts (id, slug, title, category, published_at, read_time_minutes, author, excerpt, tags_csv, content, cover_image_url, is_published, created_at, updated_at, post_order)
VALUES (1, 'entendiendo-los-macronutrientes-guia-completa', 'Entendiendo los Macronutrientes: Una Guía Completa', 'nutrition', '2025-11-06', 7, 'Carlos Olivera', 'Explorá la ciencia detrás de los macronutrientes y aprendé cómo optimizar tu dieta para mejor salud y rendimiento. Esta guía completa cubre proteínas, carbohidratos y grasas.', 'Nutrición,Artículo,Tutorial', '<p>Los macronutrientes —proteínas, carbohidratos y grasas— son la base de cualquier plan alimenticio sólido. Lejos de las dietas de moda, entender su rol fisiológico te permite tomar decisiones informadas.</p>
<h3>Proteínas</h3>
<p>Claves para la reparación y el crecimiento muscular. Fuentes: carnes magras, huevos, lácteos, legumbres.</p>

<h3>Carbohidratos</h3>
<p>Principal fuente de energía. Priorizar los complejos (arroz integral, avena, frutas, verduras) por sobre los ultra procesados.</p>

<h3>Grasas</h3>
<p>Esenciales para hormonas y salud celular. Favorecer grasas no saturadas (aceite de oliva, frutos secos, palta).</p>

<p>Recordá: ningún macro es el villano. El equilibrio y el contexto importan más que absolutismos.</p>', '/img/carloso.png', 1, '2025-11-09 15:07:14', '2025-11-09 15:07:14', 1);
INSERT INTO posts (id, slug, title, category, published_at, read_time_minutes, author, excerpt, tags_csv, content, cover_image_url, is_published, created_at, updated_at, post_order) VALUES (2, 'que-es-ethereum-y-como-funciona-simplificado-para-que-lo-entienda-tu-abuela', '¿Qué es Ethereum y como funciona? Simplificado para lo que entienda tu abuela', 'crypto', null, null, 'Carlos Olivera', 'Si alguna vez trataste de explicarle a tu abuela qué es Ethereum y terminaste diciendo algo tipo “es como una compu en la nube… pero que nadie puede apagar”, este artículo es para vos. Te cuento qué es, para qué sirve y por qué todo el mundo habla de esto, sin tecnicismos ni dolor de cabeza. Tranca, lo vas a entender… y tu abuela también.', 'Crypto,Ethereum', '<article class="post-content">

    <p class="lead">Ethereum es una plataforma descentralizada que extendió el concepto inicial de Bitcoin para convertirse en una computadora global capaz de ejecutar programas de forma distribuida, transparente e inalterable. Mientras que Bitcoin fue creado principalmente como dinero digital, Ethereum fue diseñado para habilitar <strong>software sin intermediarios</strong>, ejecutado por miles de computadoras alrededor del mundo.</p>

    <h2 class="h3 mt-4">De dinero digital a software descentralizado</h2>
    <p>La gran innovación de Ethereum fue introducir los <strong>smart contracts</strong> (contratos inteligentes): programas que se ejecutan automáticamente cuando se cumplen ciertas condiciones. No dependen de bancos, gobiernos o servidores centrales, sino que se alojan en una red distribuida llamada <strong>blockchain</strong>.</p>

    <figure class="my-4">
        <blockquote class="blockquote border-start ps-3">“Ethereum es un computador mundial que no puede apagarse, no pertenece a nadie y cualquiera puede usar”.</blockquote>
        <figcaption class="blockquote-footer">Autor desconocido</figcaption>
    </figure>

    <p>En lugar de que un programa corra en un solo servidor (como las apps tradicionales), en Ethereum se replica en miles de nodos. Esto elimina el riesgo de censura, caídas arbitrarias o manipulación de datos.</p>

    <h2 class="h3 mt-5">¿Cómo funciona Ethereum?</h2>
    <ul class="mb-4">
        <li><strong>Nodos:</strong> computadoras que almacenan una copia de la red y verifican transacciones</li>
        <li><strong>Blockchain:</strong> registro público inmutable donde se guardan datos y transacciones</li>
        <li><strong>Smart contracts:</strong> programas que automatizan acuerdos sin intermediarios</li>
        <li><strong>Ether (ETH):</strong> la moneda que paga los costos de usar la red</li>
        <li><strong>Gas:</strong> la "tarifa computacional" necesaria para ejecutar transacciones o contratos</li>
    </ul>

    <p>Cuando alguien usa Ethereum —por ejemplo, para enviar dinero o usar una aplicación descentralizada— debe pagar un costo en ETH llamado gas. Este pago compensa a los validadores de la red por la energía y procesamiento utilizados.</p>

    <h2 class="h3 mt-5">El rol del Ether (ETH)</h2>
    <p>Aunque muchas personas piensan en ETH como una inversión financiera, su propósito real es alimentar la red. Sin ETH, no se pueden publicar ni ejecutar smart contracts.</p>
    <p>Es parecido a pagar electricidad para usar tu computadora. Pero en este caso, pagas para usar la computadora global descentralizada.</p>

    <h2 class="h3 mt-5">Ejemplos reales de uso</h2>
    <ul>
        <li><strong>Finanzas descentralizadas (DeFi):</strong> préstamos, intereses y ahorro sin bancos</li>
        <li><strong>Stablecoins:</strong> dólares digitales como USDT o USDC funcionando sobre Ethereum</li>
        <li><strong>NFTs:</strong> activos digitales únicos (arte, coleccionables, identidad digital)</li>
        <li><strong>DAOs:</strong> organizaciones gestionadas por código y votación colectiva</li>
    </ul>

    <p>Estos casos muestran que Ethereum no es solo dinero, sino una infraestructura para crear nuevos modelos económicos, gobernanza digital e interacción entre usuarios sin intermediarios.</p>

    <h2 class="h3 mt-5">¿Qué lo hace diferente de Bitcoin?</h2>
    <div class="table-responsive my-3">
        <table class="table table-striped table-hover align-middle">
            <caption class="text-muted">Comparación general entre Bitcoin y Ethereum</caption>
            <thead class="table-light">
            <tr>
                <th scope="col">Característica</th>
                <th scope="col">Bitcoin</th>
                <th scope="col">Ethereum</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row">Propósito</th>
                <td>Dinero digital</td>
                <td>Computadora descentralizada</td>
            </tr>
            <tr>
                <th scope="row">¿Ejecuta programas?</th>
                <td>No</td>
                <td>Sí (Smart Contracts)</td>
            </tr>
            <tr>
                <th scope="row">Moneda</th>
                <td>BTC</td>
                <td>ETH</td>
            </tr>
            <tr>
                <th scope="row">Casos de uso</th>
                <td>Pagos, reserva de valor</td>
                <td>Apps, NFT, DeFi, DAOs, stablecoins</td>
            </tr>
            </tbody>
        </table>
    </div>

    <h2 class="h3 mt-5">Limitaciones y advertencias importantes</h2>

    <div class="alert alert-warning d-flex align-items-start" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
        <div>
            <strong>Complejidad técnica.</strong>
            Aunque la idea central es simple, el ecosistema es complejo. Entender wallets, gas, redes, tokens o contratos puede ser confuso para usuarios nuevos.
        </div>
    </div>

    <div class="alert alert-warning d-flex align-items-start" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
        <div>
            <strong>Costos variables.</strong>
            El gas sube cuando la red está congestionada. En momentos de alta demanda, enviar transacciones puede ser caro.
        </div>
    </div>

    <div class="alert alert-warning d-flex align-items-start" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
        <div>
            <strong>No es infalible.</strong>
            Los contratos inteligentes <strong>no se pueden modificar fácilmente</strong> una vez desplegados. Si tienen errores, pueden ser explotados. La red es segura, pero el código mal escrito no.
        </div>
    </div>

    <div class="alert alert-warning d-flex align-items-start" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
        <div>
            <strong>No todo es descentralizado.</strong>
            Muchas apps dicen ser “descentralizadas” pero en realidad tienen partes centralizadas (servidores, equipos controlando contratos, actualizaciones, o llaves privadas).
        </div>
    </div>

    <div class="alert alert-warning d-flex align-items-start" role="alert">
        <i class="bi bi-exclamation-triangle-fill me-2 fs-5"></i>
        <div>
            <strong>Riesgo financiero.</strong>
            ETH es un activo volátil. No debe confundirse la utilidad tecnológica de la red con el comportamiento especulativo del mercado.
        </div>
    </div>

    <h2 class="h3 mt-5">Conclusión</h2>
    <p>Ethereum no es solo money digital: es una infraestructura para redefinir cómo se construyen las aplicaciones y las finanzas en internet. Permite eliminar intermediarios mediante código, habilitar propiedad digital, automatizar acuerdos y construir nuevos modelos económicos transparentes y abiertos.</p>
    <p>Su impacto va más allá del precio del Ether: su valor reside en ser una base para la web del futuro.</p>

    <hr class="my-5" />

    <h2 class="h4">Fuente principal del contenido</h2>
    <p class="text-muted"><em>La mayor parte de la información utilizada en este artículo fue obtenida del siguiente video:</em></p>

    <div class="ratio ratio-16x9 my-3">
        <iframe src="https://www.youtube.com/embed/BHpyGy2X1t4" title="YouTube video player" allowfullscreen loading="lazy"></iframe>
    </div>

    <h2 class="h4 mt-4">Otras fuentes verificadas</h2>
    <ul>
        <li><a href="https://ethereum.org/es/what-is-ethereum/" target="_blank" rel="noopener">Ethereum.org — ¿Qué es Ethereum?</a></li>
        <li><a href="https://ethereum.org/es/developers/docs/smart-contracts/" target="_blank" rel="noopener">Ethereum.org — Smart Contracts</a></li>
    </ul>

</article>
', 'https://darksalmon-bear-873447.hostingersite.com/imgs/que-es-ethereum.png', 1, '2025-11-09 15:52:59', null, 2);
