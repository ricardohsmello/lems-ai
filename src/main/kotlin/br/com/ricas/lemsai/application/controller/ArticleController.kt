package br.com.ricas.lemsai.application.controller

import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleStructureUseCase
import br.com.ricas.lemsai.domain.util.TimeExecutionControl
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder

@RestController
@RequestMapping("article")
class ArticleController(
    val openAIService: OpenAIService,
    val createArticleUseCase: CreateArticleUseCase

) {
    lateinit var openAiResponse: StringBuilder
    @PostMapping("/create")
    fun request(
        @RequestParam theme: String,
        @RequestParam sectionNumbers: String,
        @RequestParam subSectionNumbers: String): StringBuilder? {

//        return getValue()

        return createArticleUseCase.exec(
            theme,
            sectionNumbers,
            subSectionNumbers
        ).also {
            println(it)
        }
    }

    private fun getValue() =
        StringBuilder("   1 - Introdução: A paixão pelo Corinthians\n" +
                "\n" +
                "2 - Seção 1: História e conquistas do clube\n" +
                "2.1 Subseção: Fundação e primeiros anos do Corinthians\n" +
                "2.2 Subseção: Conquistas nacionais e internacionais\n" +
                "\n" +
                "3 - Seção 2: A torcida e a cultura corintiana\n" +
                "3.1 Subseção: A Fiel Torcida e sua importância para o clube\n" +
                "3.2 Subseção: Tradições e símbolos do Corinthians\n" +
                "\n" +
                "4 - Seção 3: A rivalidade com outros clubes\n" +
                "4.1 Subseção: A rivalidade com o Palmeiras\n" +
                "4.2 Subseção: A rivalidade com o São Paulo\n" +
                "\n" +
                "5 - Seção 4: Desafios e momentos marcantes\n" +
                "5.1 Subseção: Momentos de superação e glórias no futebol\n" +
                "5.2 Subseção: Grandes derrotas e momentos difíceis na história do Corinthians\n" +
                "\n" +
                "6 - Conclusão: A grandeza do Corinthians e a fidelidade de sua torcida.")


//        return StringBuilder("\n" +
//                "Introdução: O AWS Practitioner é uma certificação oferecida pela Amazon Web Services, que valida os conhecimentos básicos e fundamentais sobre os serviços e conceitos da plataforma. Com a crescente demanda por profissionais qualificados em computação em nuvem, essa certificação é uma ótima maneira de iniciar sua jornada nesse campo promissor.\n" +
//                "\n" +
//                "A computação em nuvem tem se tornado cada vez mais importante para empresas de todos os tamanhos e setores. Ela oferece uma série de benefícios, como escalabilidade, flexibilidade, segurança e redução de custos. Com a certificação AWS Practitioner, você estará apto a entender e utilizar esses serviços de forma eficiente, se destacando no mercado de trabalho.\n" +
//                "\n" +
//                "Para obter a certificação AWS Practitioner, é necessário estudar e se preparar adequadamente. É essencial revisar os conceitos fundamentais da AWS, como os serviços básicos oferecidos pela plataforma, a arquitetura de nuvem e os modelos de consumo. Além disso, é recomendado fazer cursos e praticar com os serviços da AWS para ganhar experiência prática.\n" +
//                "\n" +
//                "A certificação AWS Practitioner traz diversos benefícios para profissionais que desejam se destacar na área de computação em nuvem. Ela demonstra um conhecimento sólido dos conceitos-chave da AWS, o que pode abrir portas para oportunidades de emprego. Além disso, a certificação é reconhecida globalmente, o que aumenta a credibilidade e confiança dos profissionais no mercado de trabalho.\n" +
//                "\n" +
//                "Com a certificação AWS Practitioner, é possível entrar no mercado de trabalho de computação em nuvem de forma mais rápida e eficiente. Profissionais certificados têm mais chances de serem contratados por empresas que utilizam a AWS e podem pleitear cargos mais altos e salários melhores. A certificação também pode abrir portas para outras certificações da AWS, permitindo um crescimento contínuo na carreira.\n" +
//                "\n" +
//                "Em resumo, a certificação AWS Practitioner é uma ótima maneira de iniciar sua jornada na área de computação em nuvem. Ela comprova seus conhecimentos fundamentais sobre a AWS e pode impulsionar sua carreira, abrindo portas para oportunidades de emprego e crescimento profissional. Se você deseja se destacar nesse mercado em expansão, considere investir na certificação AWS Practitioner e colha os benefícios que ela proporciona.\n" +
//                "\n" +
//                "\n" +
//                "Seção 1: Preparação para a certificação AWS Practitioner\n" +
//                "\n" +
//                "A certificação AWS Practitioner é um ponto de partida importante para profissionais que desejam iniciar sua carreira na área de computação em nuvem. Para se preparar adequadamente para a certificação, é essencial estudar e revisar os conceitos fundamentais da AWS.\n" +
//                "\n" +
//                "Em primeiro lugar, é importante ter um bom entendimento dos serviços básicos oferecidos pela AWS. Isso inclui serviços como Amazon EC2, Amazon S3 e Amazon RDS. Familiarize-se com as principais características e funcionalidades desses serviços, bem como com suas aplicações práticas.\n" +
//                "\n" +
//                "Além disso, é crucial compreender a arquitetura de nuvem da AWS. Isso envolve entender os diferentes componentes e recursos disponíveis na plataforma, como regiões, zonas de disponibilidade, grupos de segurança e redes virtuais. Além disso, é importante conhecer os diferentes modelos de consumo da AWS, como o modelo de Infraestrutura como Serviço (IaaS), Plataforma como Serviço (PaaS) e Software como Serviço (SaaS).\n" +
//                "\n" +
//                "Existem várias opções disponíveis para o estudo e preparação para a certificação AWS Practitioner. A Amazon oferece materiais de estudo gratuitos, como a documentação oficial da AWS, whitepapers e webinars. Além disso, existem cursos online e treinamentos práticos que podem ajudar os candidatos a obterem uma experiência prática com os serviços da AWS.\n" +
//                "\n" +
//                "Uma prática recomendada é realizar exercícios e laboratórios práticos para ganhar experiência real com as tecnologias da AWS. Isso pode ser feito usando o serviço gratuito AWS Free Tier, que permite que os usuários experimentem os serviços da AWS sem custo.\n" +
//                "\n" +
//                "Outra recomendação importante é fazer simulados de prova para se familiarizar com o formato e o tipo de perguntas que serão feitas no exame. Existem várias plataformas online que oferecem simulados de prova para a certificação AWS Practitioner.\n" +
//                "\n" +
//                "Em resumo, a preparação adequada é essencial para obter a certificação AWS Practitioner. É importante revisar os conceitos fundamentais da AWS, como serviços básicos, arquitetura de nuvem e modelos de consumo. Além disso, é recomendado fazer cursos e praticar com os serviços da AWS para ganhar experiência prática. Com a preparação correta, os profissionais estarão bem equipados para obter essa certificação e começar sua carreira na área de computação em nuvem.\n" +
//                "\n" +
//                "\n" +
//                "Seção 2: Benefícios da certificação AWS Practitioner\n" +
//                "\n" +
//                "A certificação AWS Practitioner traz uma série de benefícios para profissionais que desejam se destacar na área de computação em nuvem. \n" +
//                "\n" +
//                "Primeiramente, a certificação demonstra um conhecimento sólido dos conceitos-chave da AWS. Ao obter essa certificação, os profissionais são reconhecidos como tendo um entendimento abrangente dos serviços básicos oferecidos pela plataforma, bem como da arquitetura de nuvem e dos modelos de consumo. Isso mostra aos empregadores que o profissional possui habilidades e conhecimentos fundamentais para trabalhar com a AWS, tornando-os mais atraentes no mercado de trabalho.\n" +
//                "\n" +
//                "Além disso, a certificação AWS Practitioner é reconhecida globalmente. Isso significa que, independentemente do local onde um profissional esteja buscando oportunidades de emprego, a certificação será valorizada e respeitada. Esse reconhecimento global aumenta a credibilidade e a confiança que os empregadores têm nos profissionais certificados, o que pode levar a melhores oportunidades de trabalho.\n" +
//                "\n" +
//                "Outro benefício importante da certificação AWS Practitioner é a possibilidade de abrir portas para oportunidades de emprego. Com a demanda crescente por profissionais qualificados em computação em nuvem, ter essa certificação pode ser o diferencial necessário para se destacar em um processo de seleção. Mais e mais empresas estão adotando a AWS como sua plataforma de nuvem preferida e, portanto, buscar profissionais certificados é algo cada vez mais comum.\n" +
//                "\n" +
//                "Além disso, a certificação AWS Practitioner também pode ser um passo inicial para buscar certificações mais avançadas da AWS. Ao comprovar seu conhecimento fundamental com essa certificação básica, os profissionais podem optar por se especializar em áreas específicas da AWS, como segurança, arquitetura de soluções ou análise de dados. Com essa base sólida, é mais fácil aprender e obter outras certificações, permitindo um crescimento contínuo na carreira.\n" +
//                "\n" +
//                "Em resumo, a certificação AWS Practitioner traz uma série de benefícios para profissionais da área de computação em nuvem. Ela demonstra conhecimento sólido dos conceitos fundamentais da AWS, é reconhecida globalmente, abre portas para oportunidades de emprego e possibilita o crescimento profissional através da busca por certificações mais avançadas. Por esses motivos, investir na certificação AWS Practitioner pode ser um passo importante para impulsionar a carreira na área de computação em nuvem.\n" +
//                "\n" +
//                "\n" +
//                "Seção 3: Carreira e crescimento profissional na área de computação em nuvem com a certificação AWS Practitioner\n" +
//                "\n" +
//                "A certificação AWS Practitioner pode ser um passo crucial para o crescimento profissional na área de computação em nuvem. Ela é reconhecida globalmente e pode abrir portas para diversas oportunidades de emprego.\n" +
//                "\n" +
//                "Com a certificação AWS Practitioner em mãos, os profissionais têm mais chances de serem contratados por empresas que utilizam os serviços da AWS. Muitas organizações valorizam essa certificação como um requisito mínimo para candidatos a cargos relacionados à nuvem. Portanto, ter a certificação pode ser um diferencial competitivo no mercado de trabalho.\n" +
//                "\n" +
//                "Além disso, profissionais certificados pela AWS geralmente têm mais facilidade para pleitear cargos mais altos e salários melhores. A certificação mostra que o profissional possui um conhecimento sólido dos conceitos fundamentais da AWS, demonstrando uma capacidade de entender e utilizar eficientemente os serviços da plataforma. Com isso, os empregadores têm mais confiança em sua capacidade de lidar com projetos complexos e críticos.\n" +
//                "\n" +
//                "Outro benefício da certificação AWS Practitioner é que ela abre portas para outras certificações da AWS. Uma vez que o profissional obtenha o conhecimento básico necessário por meio dessa certificação, é possível seguir para certificações avançadas e especializadas. Isso permite um crescimento contínuo na carreira, expandindo ainda mais as oportunidades profissionais e possibilidades de especialização em áreas específicas da computação em nuvem.\n" +
//                "\n" +
//                "Além disso, a certificação também pode ser uma forma de se manter atualizado em um mercado em constante evolução. A AWS está sempre lançando novos serviços e atualizando suas funcionalidades, e a certificação AWS Practitioner fundamenta bem o profissional para acompanhar essas mudanças. Manter-se atualizado e dispor de conhecimentos atualizados é crucial para se destacar no mercado de trabalho de computação em nuvem.\n" +
//                "\n" +
//                "Em resumo, a certificação AWS Practitioner é uma excelente forma de impulsionar a carreira na área de computação em nuvem. Ela demonstra o conhecimento fundamental sobre os serviços e conceitos da AWS, aumentando as chances de sucesso no mercado de trabalho e abrindo portas para oportunidades de crescimento profissional. Portanto, se você deseja avançar nesse mercado em expansão, considere investir na certificação AWS Practitioner e aproveite todos os benefícios que ela pode trazer para sua carreira.\n" +
//                "\n" +
//                "\n" +
//                "Conclusão: A certificação AWS Practitioner é uma ótima maneira de iniciar sua jornada na área de computação em nuvem e impulsionar sua carreira. Com a validação dos conhecimentos básicos e fundamentais sobre os serviços e conceitos da AWS, você demonstra um nível de expertise que pode abrir portas para oportunidades de emprego e crescimento profissional.\n" +
//                "\n" +
//                "Uma das principais vantagens da certificação AWS Practitioner é sua relevância global. Reconhecida em diferentes países e setores, ela aumenta sua credibilidade e confiança no mercado de trabalho. Isso faz com que profissionais certificados tenham uma vantagem competitiva ao concorrer por vagas em empresas que utilizam a AWS, bem como almejar posições de maior destaque e salários mais atrativos.\n" +
//                "\n" +
//                "Além disso, a certificação AWS Practitioner pode ser um ponto de partida para outras certificações da AWS. A plataforma oferece uma ampla gama de opções para profissionais que desejam aprofundar seus conhecimentos em áreas específicas, como segurança, desenvolvimento de soluções, data analytics, entre outras. Esse crescimento contínuo na carreira é fundamental para se manter atualizado no mercado de computação em nuvem em constante mudança.\n" +
//                "\n" +
//                "Ao investir na certificação AWS Practitioner, você estará se posicionando como um profissional em nuvem capacitado e atualizado, tendo as habilidades necessárias para contribuir efetivamente com projetos e estratégias de negócio na era da computação em nuvem. Essa certificação é uma oportunidade de se diferenciar em um mercado cada vez mais competitivo e diversificado, aumentando suas chances de sucesso e realização profissional.\n" +
//                "\n" +
//                "Em suma, a certificação AWS Practitioner é um investimento valioso para quem busca entrar ou avançar na área de computação em nuvem. Ao comprovar seu conhecimento sobre os fundamentos da AWS, você estará preparado para enfrentar os desafios e aproveitar as oportunidades neste mercado em crescimento. Portanto, não perca tempo e comece a estudar e se preparar para obter a certificação AWS Practitioner e colher os benefícios que ela proporciona à sua carreira.\n")


//
//        return openAiResponse

}