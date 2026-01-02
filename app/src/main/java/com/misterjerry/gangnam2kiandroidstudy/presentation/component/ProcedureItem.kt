package com.misterjerry.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.misterjerry.gangnam2kiandroidstudy.domain.model.Procedure
import com.misterjerry.gangnam2kiandroidstudy.presentation.ui.AppColors
import com.misterjerry.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@Composable
fun ProcedureItem(procedure: Procedure) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = AppColors.gray4.copy(alpha = .5f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(vertical = 10.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 15.dp)) {
            Text(
                "Step ${procedure.step}",
                style = AppTextStyles.smallerTextBold.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier,
                text = procedure.content,
                style = AppTextStyles.smallerTextRegular.copy(color = AppColors.gray3)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ProcedureItemPreview() {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
        val mockProcedure =
            Procedure(
                recipeId = 1,
                step = 1,
                content = "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                        "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
            )

        ProcedureItem(procedure = mockProcedure)
    }


}