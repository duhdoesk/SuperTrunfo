package com.duhdoesk.supertrunfoclone.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.duhdoesk.supertrunfoclone.ui.collection.CollectionAdapter
import com.duhdoesk.supertrunfoclone.ui.collection.CollectionFragment
import com.duhdoesk.supertrunfoclone.ui.ending.GameOverFragment
import com.duhdoesk.supertrunfoclone.ui.ending.GameWonFragment
import com.duhdoesk.supertrunfoclone.ui.etc.AboutFragment
import com.duhdoesk.supertrunfoclone.ui.etc.RulesFragment
import com.duhdoesk.supertrunfoclone.ui.match.MatchFragment
import com.duhdoesk.supertrunfoclone.ui.home.HomeFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainFragmentFactory @Inject constructor(private val collectionAdapter: CollectionAdapter)
    : FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            CollectionFragment::class.java.name -> {
                CollectionFragment(collectionAdapter)
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}