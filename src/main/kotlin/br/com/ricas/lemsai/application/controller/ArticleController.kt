package br.com.ricas.lemsai.application.controller

import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
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
        @RequestParam subSectionNumbers: String): Article {

//        return getArticle()

        return createArticleUseCase.exec(
            theme,
            Integer.parseInt(sectionNumbers),
            Integer.parseInt(subSectionNumbers)
        ).also {
            println(it)
        }
    }

    private fun getArticle() =
        Article(
            durationTime = "5 minutes and 23 seconds",
            title = "Introdução \n O universo corinthiano: história, tradição e paixão",
            sections = listOf(
                Section(
//                order = 0,
                title = StringBuilder("Introdução \n O universo corinthiano: história, tradição e paixão "),
                isSubSection = false,
                content = StringBuilder("O Foo Fighters é uma banda de rock americana formada por Dave Grohl em 1994, após a trágica morte de Kurt Cobain, que encerrou a banda Nirvana. Desde então, essa icônica banda tem conquistado legiões de fãs em todo o mundo. A ascensão do Foo Fighters começou com o primeiro álbum autointitulado, lançado em 1995. Com singles como \"This Is A Call\" e \"I'll Stick Around\", a banda conseguiu angariar uma base fiel de fãs, que ficaram impressionados com o estilo incomparável de Dave Grohl e sua vocalização cativante. Os anos seguintes foram marcados por álbuns como \"The Colour and the Shape\" (1997), que continha sucessos como \"Everlong\" e \"Monkey Wrench\". Essas canções eram conhecidas por sua energia vibrante, letras emocionantes e riffs de guitarra inovadores. O Foo Fighters se firmou como uma das forças dominantes do rock alternativo da época. À medida que a banda continuava lançando álbuns, como \"There Is Nothing Left to Lose\" (1999), \"One by One\" (2002) e \"In Your Honor\" (2005), eles continuavam a explorar novos sons e a amadurecer como músicos. Em cada lançamento, o Foo Fighters se reinventava e mostrava sua versatilidade musical. No entanto, foi com o álbum \"Wasting Light\" (2011) que o Foo Fighters recebeu os maiores elogios da crítica. O álbum foi gravado em fitas analógicas em um antigo estúdio de garagem, o que resultou em uma experiência sonora crua e autêntica. O single \"Rope\" foi lançado e logo se tornou um sucesso, aumentando ainda mais a popularidade da banda. Quando se trata do legado do Foo Fighters, é impossível não mencionar suas apresentações ao vivo. A banda é conhecida por sua energia contagiante e paixão no palco. Os concertos do Foo Fighters são verdadeiras experiências transcendentais, com seus hinos empolgantes sendo cantados em coro por multidões de fãs. Além disso, o Foo Fighters também é conhecido por suas colaborações com outros artistas icônicos, como Paul McCartney, Queen, Mick Jagger e muitos outros. Essas colaborações reforçam sua posição como um dos grandes nomes do rock. Em suma, o Foo Fighters é mais do que apenas uma banda de rock. Eles são uma instituição, uma força que desafia as convenções e continua a inspirar uma nova geração de músicos e fãs. Sua ascensão meteórica e seu legado duradouro são testemunhos de seu talento e dedicação incansável à música. Se você ainda não conhece o mundo do Foo Fighters, está na hora de explorar e descobrir por que eles são considerados uma das maiores e mais icônicas bandas da história do rock. ")
                ),
                Section(
//                    order = 1,
                    title = StringBuilder("Seção 1: O Despertar do Foo Fighters: Uma Jornada Pelas Raízes e Influências da Banda Icônica "),
                    isSubSection = false,
                    content = StringBuilder("A banda norte-americana Foo Fighters representa um marco na história do rock contemporâneo. Formada em 1994 por Dave Grohl, ex-baterista do Nirvana, a banda adquiriu uma legião de fãs ao redor do mundo com seu som enérgico e refrãos marcantes. No entanto, para entender a jornada do Foo Fighters, é necessário voltar às raízes musicais de Grohl. Antes de formar a banda, ele já tinha uma longa carreira como músico, tendo passado por diversas bandas locais de Washington D.C. Como baterista do Nirvana, Grohl contribuiu para a consolidação do grunge e se tornou parte integrante de uma das maiores bandas da década de 90. Após a morte trágica de Kurt Cobain, Grohl sentiu a necessidade de se expressar musicalmente novamente. Compondo e gravando uma série de músicas solo em segredo, ele decidiu criar uma banda para apresentar suas criações. Assim, o Foo Fighters nasceu. O primeiro álbum homônimo da banda, lançado em 1995, foi gravado quase inteiramente por Grohl, que tocou todos os instrumentos, além de assumir os vocais. O som do Foo Fighters era uma mistura de influências do punk, rock alternativo e grunge, com uma pegada única e melódica. Ao longo dos anos, o Foo Fighters consolidou sua posição como uma das bandas mais importantes da cena do rock. A capacidade de Grohl de compor melodias cativantes e letras sinceras se tornou uma marca registrada da banda. Hits como \"Everlong\", \"Learn to Fly\" e \"The Pretender\" são exemplos desse talento. Além das influências do grunge e do rock alternativo, o Foo Fighters também incorporou outros gêneros musicais em sua discografia. Em álbuns posteriores, como \"One by One\", \"In Your Honor\" e \"Wasting Light\", a banda explorou elementos do hard rock, do punk e até mesmo do pop. Mais do que apenas criar música, o Foo Fighters representa uma atitude de perseverança e paixão pelo rock. Grohl e seus companheiros de banda estão sempre em busca de novos horizontes musicais, mantendo-se fieis às suas raízes ao mesmo tempo. Em resumo, o despertar do Foo Fighters representa a vitalidade do rock contemporâneo. Uma jornada pelas raízes e influências da banda nos revela um grupo de músicos talentosos que, ao longo de sua trajetória, souberam se reinventar e ultrapassar barreiras musicais. Seu legado é marcado pela fusão de diferentes estilos e pela capacidade de criar músicas que ressoam profundamente nos corações dos fãs. O Foo Fighters é a prova de que o rock ainda tem muito a oferecer e que sua influência é e sempre será icônica.")
                )
                ,
                Section(
//                    order = 2,
                    title = StringBuilder("SubSeção 1: As Raízes e Influências do Foo Fighters: Um Despertar Musical na Seção 1 "),
                    isSubSection = true,
                    content = StringBuilder("A história do Foo Fighters é uma mistura fascinante de raízes musicais e influências diversas. A banda, formada por Dave Grohl após o fim do Nirvana, nasceu da necessidade de expressar sua criatividade musical de uma forma única. As raízes do Foo Fighters remontam às experiências de Grohl no Nirvana, onde ele aprendeu muito sobre a indústria da música e sobre como compor e tocar com intensidade. A morte de Kurt Cobain foi um momento significativo para Grohl, que decidiu seguir em frente e encontrar sua própria voz musical. A influência do punk rock foi fundamental para o som do Foo Fighters. Grohl cresceu ouvindo bandas como Black Flag e Bad Brains, o que contribuiu para a energia e agressividade presentes nas composições da banda. Além disso, o rock alternativo dos anos 90 também teve um papel importante, com bandas como Pixies e Mudhoney inspirando a abordagem musical do Foo Fighters. No entanto, o Foo Fighters também explorou outras influências ao longo de sua carreira. Grohl sempre foi um fã de música clássica, jazz e até mesmo música country. Essas influências se destacam em baladas melódicas como \"Everlong\" e \"Times Like These\", mostrando a diversidade e a versatilidade da banda. O despertar musical do Foo Fighters ocorreu na Seção 1 de sua carreira, com o lançamento do álbum de estreia homônimo. Nesse momento, a banda encontrou sua identidade e consolidou seu som característico. O álbum foi recebido com entusiasmo pela crítica e pelo público, e marcou o início de uma jornada musical bem-sucedida. Em suma, as raízes e influências do Foo Fighters são diversas e complexas. Desde o punk rock agressivo até a melodia refinada, a banda encontrou uma maneira única de combinar diferentes estilos e criar algo autêntico. Com sua energia contagiante e letras sinceras, o Foo Fighters despertou e continua a despertar o interesse de fãs em todo o mundo.")
                ),
                Section(
//                    order = 3,
                    title = StringBuilder("Conclusao"),
                    isSubSection = false,
                    content = StringBuilder("O artigo \"Introdução ao Mundo do Foo Fighters: Descubra a Ascensão e o Legado Dessa Banda Icônica\" oferece uma visão valiosa sobre a história da banda e seu impacto duradouro na cena musical. Na seção inicial, intitulada \"O Despertar do Foo Fighters: Uma Jornada Pelas Raízes e Influências da Banda Icônica\", somos levados a uma viagem fascinante pelas origens do grupo e suas inspirações. Ao mergulhar nas raízes do Foo Fighters, podemos apreciar a notável transição de Dave Grohl - ex-baterista do Nirvana - para líder e vocalista dessa nova banda. O autor do artigo oferece detalhes vívidos sobre como Grohl se destacou nos anos seguintes ao fim prematuro da icônica banda grunge. Sua música, uma mistura única de rock alternativo e punk, rapidamente cativou o público, e o Foo Fighters nasceu então. Um aspecto particularmente interessante da seção é a exploração das influências que moldaram o som característico do Foo Fighters. O autor mergulha fundo nos gostos musicais de Grohl e revela suas paixões por bandas como Led Zeppelin, The Beatles e The Rolling Stones. Essas influências notáveis podem ser ouvidas claramente nas composições energéticas e cativantes da banda. Além disso, o artigo aborda também as características únicas que contribuíram para a ascensão do Foo Fighters como uma força dominante no cenário musical. A habilidade de Grohl como compositor talentoso e a capacidade dos membros da banda de criar um som coeso e poderoso são aspectos que são minuciosamente explorados pelo autor. Esses fatores, juntamente com a intensidade de suas performances ao vivo, ajudaram a firmar a reputação do Foo Fighters como uma banda verdadeiramente icônica. No decorrer da seção, o autor também nos presenteia com informações sobre os primeiros lançamentos da banda e seu crescimento gradual no cenário musical. Através de uma análise cuidadosa, somos informados sobre o impacto crítico e comercial desses álbuns e como eles ajudaram a estabelecer o Foo Fighters como uma força musical em constante ascensão. Por fim, a seção \"O Despertar do Foo Fighters\" apresenta uma conclusão forte e persuasiva sobre o poder duradouro do legado da banda. O artigo nos deixa ansiosos para continuar lendo e descobrir mais sobre as conquistas do Foo Fighters e como eles se tornaram uma das bandas mais icônicas e amadas de todos os tempos. Em suma, o artigo \"Introdução ao Mundo do Foo Fighters: Descubra a Ascensão e o Legado Dessa Banda Icônica\" proporciona uma visão abrangente e envolvente sobre a história do Foo Fighters. Através de uma análise detalhada das raízes e influências da banda, somos transportados para o seu mundo musical único. Este artigo é uma leitura obrigatória para qualquer fã de música que deseja entender a ascensão e o legado desse grupo icônico.")
                )
            )
        )

}