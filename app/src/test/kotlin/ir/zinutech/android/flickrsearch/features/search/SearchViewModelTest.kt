package ir.zinutech.android.flickrsearch.features.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import ir.zinutech.android.flickrsearch.domain.features.search.models.FlickrPhoto
import ir.zinutech.android.flickrsearch.domain.features.search.repositories.SearchRepository
import ir.zinutech.android.flickrsearch.domain.features.search.usecases.SearchUseCase
import ir.zinutech.android.flickrsearch.features.search.SearchViewModel.SearchUiState.EmptyResult
import ir.zinutech.android.flickrsearch.features.search.SearchViewModel.SearchUiState.Error
import ir.zinutech.android.flickrsearch.features.search.SearchViewModel.SearchUiState.Idle
import ir.zinutech.android.flickrsearch.features.search.SearchViewModel.SearchUiState.Loading
import ir.zinutech.android.flickrsearch.features.search.SearchViewModel.SearchUiState.Success
import ir.zinutech.android.flickrsearch.utils.MainDispatcherRule
import ir.zinutech.android.flickrsearch.utils.getOrAwaitValue
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.SocketTimeoutException

class SearchViewModelTest {

  @get:Rule
  val mainDispatcherRule = MainDispatcherRule()

  @get:Rule
  val mockkRule = MockKRule(this)

  @MockK
  lateinit var searchRepository: SearchRepository

  private lateinit var viewModel: SearchViewModel

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Before
  fun setUp() {
    viewModel = SearchViewModel(
      searchUseCase = SearchUseCase(searchRepository),
    )
  }

  @Test
  fun `when no search query, state is idle`() = runTest {
    coEvery { searchRepository.search(any()) } returns emptyList()
    val awaitValue = viewModel.uiState.getOrAwaitValue(afterObserve = ::advanceUntilIdle)
    assertThat(awaitValue).isInstanceOf(Idle::class)
  }

  @Test
  fun `when query has no result, state goes from loading to emptyResult`() = runTest {
    coEvery { searchRepository.search(any()) } returns emptyList()
    viewModel.onQueryChanged("test")
    val awaitValue = viewModel.uiState.getOrAwaitValue(afterObserve = ::advanceUntilIdle)
    assertThat(awaitValue).isInstanceOf(Loading::class)
    assertThat(viewModel.uiState.getOrAwaitValue()).isInstanceOf(EmptyResult::class)
  }

  @Test
  fun `when query has result, state goes from loading to success`() = runTest {
    coEvery { searchRepository.search(any()) } returns createTestPhotos()
    viewModel.onQueryChanged("test")
    val awaitValue = viewModel.uiState.getOrAwaitValue(afterObserve = ::advanceUntilIdle)
    assertThat(awaitValue).isInstanceOf(Loading::class)
    assertThat(viewModel.uiState.getOrAwaitValue()).isInstanceOf(Success::class)
  }

  @Test
  fun `when query has result, state is success and is filled correctly`() = runTest {
    coEvery { searchRepository.search(any()) } returns createTestPhotos()
    viewModel.onQueryChanged("test")
    val awaitValue = viewModel.uiState.getOrAwaitValue(afterObserve = ::advanceUntilIdle)
    assertThat(awaitValue).isInstanceOf(Loading::class)
    assertThat(viewModel.uiState.getOrAwaitValue()).all {
      isInstanceOf(Success::class)
      this.transform { it as Success }.prop(Success::photos).all {
        hasSize(10)
        transform(transform = List<FlickrPhoto>::first).prop(FlickrPhoto::title).isEqualTo("title1")
        transform(transform = List<FlickrPhoto>::last).prop(FlickrPhoto::title).isEqualTo("title10")
      }
    }
  }

  @Test
  fun `when query fails, state is error`() = runTest {
    coEvery { searchRepository.search(any()) } throws SocketTimeoutException()
    viewModel.onQueryChanged("test")
    val awaitValue = viewModel.uiState.getOrAwaitValue(afterObserve = ::advanceUntilIdle)
    assertThat(awaitValue).isInstanceOf(Loading::class)
    assertThat(viewModel.uiState.getOrAwaitValue()).all {
      isInstanceOf(Error::class)
      transform { it as Error }.prop(Error::exception).isInstanceOf(SocketTimeoutException::class)
    }
  }

  private fun createTestPhotos(): List<FlickrPhoto> {
    return (1..10).map {
      FlickrPhoto(
        id = "id$it",
        title = "title$it",
        url = "https://url$it"
      )
    }
  }
}