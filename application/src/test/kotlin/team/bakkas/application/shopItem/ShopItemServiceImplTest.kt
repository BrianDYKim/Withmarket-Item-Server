package team.bakkas.application.shopItem

import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
internal class ShopItemServiceImplTest {

    @Test
    @DisplayName("Basic Test")
    fun test1() {
        val message = "Hello, world!"

        assertEquals(message, "Hello, world!")
    }

}