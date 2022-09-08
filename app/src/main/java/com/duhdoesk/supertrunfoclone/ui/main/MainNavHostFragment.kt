package com.duhdoesk.supertrunfoclone

import android.app.Activity
import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.duhdoesk.supertrunfoclone.ui.collection.CollectionFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainNavHostFragment: NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: CollectionFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = fragmentFactory
    }
}