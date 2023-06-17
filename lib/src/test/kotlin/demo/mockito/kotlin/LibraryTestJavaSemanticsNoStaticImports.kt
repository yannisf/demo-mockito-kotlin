package demo.mockito.kotlin

import org.assertj.core.api.Assertions
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import kotlin.test.Test

class LibraryTestJavaSemanticsNoStaticImports {

    @Test
    fun `testing in java`() {
        val supporterMock = Mockito.mock(Supporter::class.java)
        val librarySpy = Mockito.spy(Library(supporterMock))

        Mockito.`when`(librarySpy.basicSolution(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(10)

        val outcome = librarySpy.calculate(1, "hello", "world")

        Assertions.assertThat(outcome)
                .isNotEqualTo(42)
                .isEqualTo(10)

        Mockito.verify(librarySpy, Mockito.times(1)).basicSolution(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
        Mockito.verify(librarySpy, Mockito.never()).alternativeSolution(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())
    }

}
