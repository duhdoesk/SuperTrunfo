package com.duhdoesk.supertrunfoclone.ui.collection

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class CollectionFragmentFactory @Inject constructor(private val collectionAdapter: CollectionAdapter)
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