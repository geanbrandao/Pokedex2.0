package br.dev.geanbrandao.howtodo.newpokedex.presentation.common

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScreenUiState(
    val isLoading: Boolean = false,
    val error: Exception? = null
) : Parcelable