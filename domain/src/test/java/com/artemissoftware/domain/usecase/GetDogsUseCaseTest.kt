package com.artemissoftware.domain.usecase

import com.artemissoftware.common.Resource
import com.artemissoftware.domain.ApiNetworkResponse
import com.artemissoftware.domain.BaseUseCaseTest
import com.artemissoftware.domain.model.Dog
import com.artemissoftware.domain.repository.DogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import org.mockito.kotlin.times
import kotlin.test.assertEquals

class GetDogsUseCaseTest : BaseUseCaseTest() {


    private lateinit var dogRepository: DogRepository
    private lateinit var getDogsUseCase: GetDogsUseCase


    @Before
    fun setUp() {
        dogRepository = mock()
        getDogsUseCase = GetDogsUseCase(repository = dogRepository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `Should return Unit`() = runBlockingTest {

        val dog = Dog(name = "Akita", breedGroup = "Working", temperament="Docile, Alert, Responsive, Dignified, Composed, Friendly, Receptive, Faithful, Courageous", imageUrl = "https://cdn2.thedogapi.com/images/S1_8kx5Nm_1280.jpg", lifeSpan= "10 - 14 years")
        val list = listOf<Dog>(dog)

        whenever(dogRepository.getDogs()).thenReturn(
            ApiNetworkResponse(list)
        )

//        getDogsUseCase.invoke().apply {
//
//            val oo = it
//
//            assertEquals(this.javaClass, Flow<Resource<List<Dog>>>>::class.java)
//        }

        // Execute the use-case
        val emissions: List<Resource<List<Dog>>> = getDogsUseCase.invoke().toList()
        val lolo = (emissions[0] as Resource)
        assert(lolo.data?.get(0)?.name  ==  dog.name)

        //assertEquals(lolo.javaClass, Resource::class.java)


//        whenever(authenticationRepository.validateEmail(nif = nif, email = email)).thenReturn(
//            ApiNetworkResult(validateEmailEntity)
//        )
//
//        validateEmailByEDPUseCase.invoke(nif = nif, email = email).apply {
//            assertEquals(this.javaClass, ValidateEmailEntity::class.java)
//        }
//
//        verify(authenticationRepository, times(1)).validateEmail(nif = nif, email = email)


    }



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