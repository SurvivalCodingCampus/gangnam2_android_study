package com.survivalcoding.gangnam2kiandroidstudy.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.survivalcoding.gangnam2kiandroidstudy.presentation.ui.AppTextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    time: String?,
    rate: String?,
    category: String?,
    inputText: String?,
    onDismiss: (inputText: String?, time: String?, rate: String?, category: String?, enableBottomSheet: Boolean) -> Unit
) {
    val selectedTime = remember { mutableStateOf<String>(time ?: "") }
    val selectedRate = remember { mutableStateOf<String>(rate ?: "") }
    val selectedCategory = remember { mutableStateOf<String>(category ?: "") }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )
    ModalBottomSheet(
        dragHandle = null,
        sheetState = sheetState,
        onDismissRequest = {
            onDismiss(
                inputText,
                selectedTime.value,
                selectedRate.value,
                selectedCategory.value,
                true
            )
            Log.d(
                "FilterBottomSheet",
                "onDismissRequest: ${selectedTime.value}, ${selectedRate.value}, ${selectedCategory.value}"
            )
        }) {
        Column(modifier = Modifier.padding(horizontal = 30.dp)) {
            Spacer(modifier = Modifier.height(31.dp))
            Text(
                "Filter Search",
                modifier = Modifier.fillMaxWidth(),
                style = AppTextStyles.smallTextBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Time", modifier = Modifier.fillMaxWidth(), style = AppTextStyles.smallTextBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                FilterButton("All", selectedTime.value == ("All")) {
                    if (selectedTime.value == ("All")) {
                        selectedTime.value = ""
                    } else {
                        selectedTime.value = "All"
                    }

                }
                FilterButton("Newest", selectedTime.value == ("Newest")) {
                    if (selectedTime.value == ("Newest")) {
                        selectedTime.value = ""
                    } else {
                        selectedTime.value = "Newest"
                    }

                }
                FilterButton("Oldest", selectedTime.value == ("Oldest")) {
                    if (selectedTime.value == ("Oldest")) {
                        selectedTime.value = ""
                    } else {
                        selectedTime.value = "Oldest"
                    }

                }
                FilterButton("Popularity", selectedTime.value == ("Popularity")) {
                    if (selectedTime.value == ("Popularity")) {
                        selectedTime.value = ""
                    } else {
                        selectedTime.value = "Popularity"
                    }
                }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Rate", modifier = Modifier.fillMaxWidth(), style = AppTextStyles.smallTextBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                RatingButton("5", selectedRate.value == ("5")) {
                    if (selectedRate.value == ("5")) {
                        selectedRate.value = ""
                    } else {
                        selectedRate.value = "5"
                    }
                }
                RatingButton("4", selectedRate.value == ("4")) {
                    if (selectedRate.value == ("4")) {
                        selectedRate.value = ""
                    } else {
                        selectedRate.value = "4"
                    }
                }
                RatingButton("3", selectedRate.value == ("3")) {
                    if (selectedRate.value == ("3")) {
                        selectedRate.value = ""
                    } else {
                        selectedRate.value = "3"
                    }
                }
                RatingButton("2", selectedRate.value == ("2")) {
                    if (selectedRate.value == ("2")) {
                        selectedRate.value = ""
                    } else {
                        selectedRate.value = "2"
                    }
                }
                RatingButton("1", selectedRate.value == ("1")) {
                    if (selectedRate.value == ("1")) {
                        selectedRate.value = ""
                    } else {
                        selectedRate.value = "1"
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Category", modifier = Modifier.fillMaxWidth(), style = AppTextStyles.smallTextBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) { //TODO 나중에 FlowRow로 리펙터링
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    FilterButton("All", selectedCategory.value == ("All")) {
                        if (selectedCategory.value == ("All")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "All"
                        }

                    }
                    FilterButton("Cereal", selectedCategory.value == ("Cereal")) {
                        if (selectedCategory.value == ("Cereal")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Cereal"
                        }
                    }
                    FilterButton("Vegetables", selectedCategory.value == ("Vegetables")) {
                        if (selectedCategory.value == ("Vegetables")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Vegetables"
                        }
                    }
                    FilterButton("Dinner", selectedCategory.value == ("Dinner")) {
                        if (selectedCategory.value == ("Dinner")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Dinner"
                        }
                    }


                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    FilterButton("Chinese", selectedCategory.value == ("Chinese")) {
                        if (selectedCategory.value == ("Chinese")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Chinese"
                        }
                    }
                    FilterButton("Local Dish", selectedCategory.value == ("Local Dish")) {
                        if (selectedCategory.value == ("Local Dish")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Local Dish"
                        }
                    }
                    FilterButton("Fruit", selectedCategory.value == ("Fruit")) {
                        if (selectedCategory.value == ("Fruit")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Fruit"
                        }
                    }
                    FilterButton("BreakFast", selectedCategory.value == ("BreakFast")) {
                        if (selectedCategory.value == ("BreakFast")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "BreakFast"
                        }
                    }
                }
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    FilterButton("Spanish", selectedCategory.value == ("Spanish")) {
                        if (selectedCategory.value == ("Spanish")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Spanish"
                        }
                    }
                    FilterButton("Chinese", selectedCategory.value == ("Chinese")) {
                        if (selectedCategory.value == ("Chinese")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Chinese"
                        }
                    }
                    FilterButton("Lunch", selectedCategory.value == ("Lunch")) {
                        if (selectedCategory.value == ("Lunch")) {
                            selectedCategory.value = ""
                        } else {
                            selectedCategory.value = "Lunch"
                        }
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    SmallButton("Filter") {
                        onDismiss(
                            inputText,
                            selectedTime.value,
                            selectedRate.value,
                            selectedCategory.value,
                            false
                        )

                    }
                }
                Spacer(modifier = Modifier.height(22.dp))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//private fun FilterBottomSheetPreview() {
//    FilterBottomSheet({})
//}