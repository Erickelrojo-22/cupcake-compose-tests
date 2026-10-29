package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.ui.StartOrderScreen
import org.junit.Rule
import org.junit.Test

class CupcakeOrderScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        flavors.forEach { flavor ->
            composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startOrderScreen_verifyContent() {
        composeTestRule.setContent {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {},
                modifier = Modifier.fillMaxSize()
            )
        }

        composeTestRule.onNodeWithStringId(R.string.order_cupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.one_cupcake).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.six_cupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.twelve_cupcakes).assertIsDisplayed()
    }

    @Test
    fun orderSummaryScreen_verifyContent() {
        val order = OrderUiState(
            quantity = 6,
            flavor = "Chocolate",
            date = "Fri Oct 30",
            price = "$12.00"
        )
        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = order,
                onCancelButtonClicked = {},
                onSendButtonClicked = { _, _ -> },
                modifier = Modifier.fillMaxSize()
            )
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.resources.getQuantityString(R.plurals.cupcakes, 6, 6)
        ).assertIsDisplayed()
        composeTestRule.onNodeWithText("Chocolate").assertIsDisplayed()
        composeTestRule.onNodeWithText("Fri Oct 30").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, "$12.00")
        ).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.send).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.cancel).assertIsDisplayed()
    }

    @Test
    fun selectOptionScreen_selectFlavor_enablesNextButton() {
        composeTestRule.setContent {
            SelectOptionScreen(subtotal = "$100", options = listOf("Vanilla", "Chocolate"))
        }

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
        composeTestRule.onNodeWithText("Chocolate").performClick()
        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}
