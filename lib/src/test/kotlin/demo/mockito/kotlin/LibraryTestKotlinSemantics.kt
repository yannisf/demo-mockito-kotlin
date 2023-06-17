package demo.mockito.kotlin

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Disabled
import org.mockito.kotlin.*
import kotlin.test.Test

class LibraryTestKotlinSemantics {

    @Test
    fun `testing in kotlin (simple case)`() { //Does not work with mockito-core:4.8.1 which is the default for up to spring boot 3.1.0!!!
        val supporterMock: Supporter = mock() //Type is inferred!
        //val supporterMock = mock<Supporter>() //Alternative notation
        val librarySpy = spy(Library(supporterMock)) {
            on { basicSolution("hello", "world") } doReturn 10
        }

        val actual = librarySpy.calculate(1, "hello", "world")

        assertThat(actual)
                .isNotEqualTo(42)
                .isEqualTo(10)

        verify(librarySpy, times(1)).basicSolution(any(), any())
        verify(librarySpy, never()).alternativeSolution(any(), any())
    }

    @Test
    @Disabled
    fun `testing in kotlin (does not work due to any() matchers)`() {
        val supporterMock: Supporter = mock()

        val librarySpy = spy(Library(supporterMock)) {
            on { basicSolution(any(), any()) } doReturn 10 //DOES NOT WORK: String instead of String?
        }

        val actual = librarySpy.calculate(1, "hello", "world")

        assertThat(actual)
                .isNotEqualTo(42)
                .isEqualTo(10)

        verify(librarySpy, times(1)).basicSolution(any(), any())
        verify(librarySpy, never()).alternativeSolution(any(), any())
    }


    @Test
    fun `testing in kotlin (works as the actual method invocation is skipped)`() {
        val supporterMock: Supporter = mock()
        val librarySpy = spy(Library(supporterMock))

        doReturn(10).whenever(librarySpy).basicSolution(any(), any()) //any works in this case, as we do not actually invoke the method

        val actual = librarySpy.calculate(1, "hello", "world")

        assertThat(actual)
                .isNotEqualTo(42)
                .isEqualTo(10)

        verify(librarySpy, times(1)).basicSolution(any(), any())
        verify(librarySpy, never()).alternativeSolution(any(), any())
    }

    @Test
    fun `testing in kotlin (new doReturn syntax)`() {
        val supporterMock: Supporter = mock()
        val librarySpy = spy(Library(supporterMock)) {
            doReturn(10).on { basicSolution(any(), any()) } //Requires mockito-kotlin 5.0.0, which requires some more options in gradle build and java17
        }

        val actual = librarySpy.calculate(1, "hello", "world")

        assertThat(actual)
                .isNotEqualTo(42)
                .isEqualTo(10)

        verify(librarySpy, times(1)).basicSolution(any(), any())
        verify(librarySpy, never()).alternativeSolution(any(), any())
    }

    @Test
    fun `testing in kotlin (captors promote robustness)`() {
        val supporterMock: Supporter = mock()
        val librarySpy = spy(Library(supporterMock)) {
            on { basicSolution("hello", "world") } doReturn 10
        }

        val actual = librarySpy.calculate(1, "hello", "world")

        assertThat(actual)
                .isNotEqualTo(42)
                .isEqualTo(10)

        val captor = argumentCaptor<String>()

        verify(librarySpy, times(1)).basicSolution(captor.capture(), captor.capture())
        verify(librarySpy, never()).alternativeSolution(any(), any())

        assertThat(captor.firstValue).isEqualTo("hello")
        assertThat(captor.secondValue).isEqualTo("world")
    }


}
