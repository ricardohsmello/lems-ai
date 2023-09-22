import br.com.ricas.lemsai.LemsAiApplication
import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import br.com.ricas.lemsai.domain.usecase.impl.OpenAIRequestUseCaseImpl
import br.com.ricas.lemsai.fakedata.fakeOpenAIResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
@SpringBootTest(classes = [LemsAiApplication::class])
class OpenAIRequestUseCaseImplTest {

    private lateinit var service: OpenAIRequestUseCase

    @MockK
    private lateinit var openAIConfig: OpenAIConfig

    @MockK
    private lateinit var openAIRequestPort: OpenAIRequestPort

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        service = OpenAIRequestUseCaseImpl(
            openAIConfig = openAIConfig,
            openAIRequestPort = openAIRequestPort
        )
    }

    @Test
    fun testRequestAnswer() {
        val inputContent = "Hello, chatbot!"
        val expectedResponse = "Chatbot response here."

        every { openAIConfig.apiModel() } returns "gpt-mock"
        every { openAIConfig.apiKey() } returns "api-mock"
        every { openAIConfig.apiURL() } returns "url-mock"

        every {
            openAIRequestPort.requestChatGPT(
                any(),
                any(),
                any(),
                any()
            )
        } returns fakeOpenAIResponse

        val result = service.requestAnswer(inputContent)

        assertEquals(expectedResponse, result.toString())
    }
}