package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.data.model.Procedure
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppColors
import com.survivalcoding.gangnam2kiandroidstudy.ui.AppTextStyles

@Composable
fun ProcedureCard(procedure: Procedure, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            Text(
                "Step ${procedure.step}",
                fontWeight = FontWeight.SemiBold,
                style = AppTextStyles.normalTextRegular
            )
            Text(
                procedure.content,
                modifier = Modifier.padding(top = 5.dp),
                color = AppColors.gray3,
                style = AppTextStyles.smallTextRegular
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProcedureCardPreview() {
    ProcedureCard(Procedure(1, 1, "Preheat the oven to 350°F (175°C)."))
}