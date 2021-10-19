package com.artemissoftware.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class GetDogsUseCaseTest {


//    private lateinit var authenticationRepository: AuthenticationRepository
//    private lateinit var recoverPasswordUseCache: RecoverPasswordUseCache
//
//
//
//    @Before
//    fun setUp() {
//        authenticationRepository = mock()
//        recoverPasswordUseCache = RecoverPasswordUseCacheImpl(
//            dispatcher = Dispatchers.Main,
//            authenticationRepository = authenticationRepository
//        )
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `Should return Unit`() = runBlockingTest {
//
//        val email = "hugo.samuel.almeida@axians.com"
//
//        whenever(authenticationRepository.recoverPassword(email)).thenReturn(
//            ApiNetworkResult(Unit)
//        )
//
//        recoverPasswordUseCache.invoke(email)
//        verify(authenticationRepository, times(1)).recoverPassword(email)
//    }
//
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `Should return InvalidParametersException`() = runBlockingTest {
//
//        val email = ""
//
//        val error = ApiNetworkErrorEntity(
//            message = UserRegisterUseCaseImpl.INVALID_PARAMETERS
//        )
//
//
//        whenever(authenticationRepository.recoverPassword(email)).thenReturn(
//            ApiNetworkResult(error = error)
//        )
//
//        assertFailsWith<InvalidParametersException> { recoverPasswordUseCache.invoke(email) }.apply {
//            Assert.assertEquals(this.javaClass, InvalidParametersException::class.java)
//        }
//
//        verify(authenticationRepository, times(1)).recoverPassword(email)
//    }
//
//
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `Should throw UnknownAPIException`() = runBlockingTest {
//
//        val email = "hugo.samuel.almeida@axians.com"
//
//        val error = ApiNetworkErrorEntity()
//
//        whenever(authenticationRepository.recoverPassword(email)).thenReturn(
//            ApiNetworkResult(error = error)
//        )
//
//        assertFailsWith<UnknownAPIException> { recoverPasswordUseCache.invoke(email) }.apply {
//            Assert.assertEquals(this.javaClass, UnknownAPIException::class.java)
//        }
//
//        verify(authenticationRepository, times(1)).recoverPassword(email)
//    }
}