import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.smartexpensetracker.onBoarding.IndicatorRow
import com.example.smartexpensetracker.onBoarding.OnBoarding2
import com.example.smartexpensetracker.onBoarding.OnBoarding3
import com.example.smartexpensetracker.onBoarding.OnBoardingScreen
import com.example.smartexpensetracker.onBoarding.SignIn
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable

fun SwipingAnimation() {
    val navController = rememberNavController()
    val pagerState = rememberPagerState(initialPage = 0)

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            count = 3,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> OnBoardingScreen()
                1 -> OnBoarding2()
                2 -> OnBoarding3()
            }
        }
        IndicatorRow(currentPage = pagerState.currentPage)
        SignIn()
    }
}
