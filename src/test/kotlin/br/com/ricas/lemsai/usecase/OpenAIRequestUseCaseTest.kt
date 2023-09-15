import br.com.ricas.lemsai.LemsAiApplication
import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import br.com.ricas.lemsai.fakedata.fakeOpenAIResponse
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [LemsAiApplication::class])
class OpenAIRequestUseCaseImplTest {

    @Autowired
    private lateinit var openAIRequestUseCase: OpenAIRequestUseCase

    @MockK
    private lateinit var openAIRequestPort: OpenAIRequestPort

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testRequestAnswer() {
        val inputContent = "Hello, chatbot!"
        val expectedResponse = "Chatbot response here."

        // Mock the behavior of the OpenAIConfig and OpenAIRequestPort
//        every { openAIConfig.apiModel() } just Runs
//        every { openAIConfig.apiKey() } returns any()
//        every { openAIConfig.apiURL() } returns any()

        every {
            openAIRequestPort.requestChatGPT(
                any(),
                any(),
                any(),
                any()
            )
        } returns fakeOpenAIResponse

        // Call the method to be tested
        val result = openAIRequestUseCase.requestAnswer(inputContent)

        // Assert the result
        assertEquals(expectedResponse, result.toString())
    }
}
