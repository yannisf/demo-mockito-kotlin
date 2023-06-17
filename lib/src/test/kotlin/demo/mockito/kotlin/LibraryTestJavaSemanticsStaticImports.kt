package demo.mockito.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.mockito.Mockito.*
import kotlin.test.Test

class LibraryTestJavaSemanticsStaticImports {

    @Test
    fun `testing in java`() {
        val supporterMock = mock(Supporter::class.java)
        val librarySpy = spy(Library(supporterMock))

        `when`(librarySpy.basicSolution(anyString(), anyString())).thenReturn(10)

        val outcome = librarySpy.calculate(1, "hello", "world")

        assertThat(outcome)
                .isNotEqualTo(42)
                .isEqualTo(10)

        verify(librarySpy, times(1)).basicSolution(anyString(), anyString())
        verify(librarySpy, never()).alternativeSolution(anyString(), anyString())
    }

}
