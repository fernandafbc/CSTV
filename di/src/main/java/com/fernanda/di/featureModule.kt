package com.fernanda.di

import com.fernanda.match_details.MatchDetailsViewModel
import com.fernanda.matches_list.MatchesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel {
        MatchesListViewModel()
    }
    viewModel {
        MatchDetailsViewModel()
    }
}