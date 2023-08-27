//package com.example.videoexoplayer.ui.base
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.videoexoplayer.ui.main.MainViewModel
//import java.security.Provider
//
////class MyViewModelFactory(): ViewModelProvider.Factory{
////    override fun <T : ViewModel> create(modelClass: Class<T>): T {
////        if(modelClass.isAssignableFrom(BaseViewModel::class.java)){
////            return ViewModel
////        }
////        throw IllegalArgumentException ("UnknownViewModel")
////    }
////
////}
//
////@Singleton
//class ViewModelFactory constructor(private  val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>): ViewModelProvider.Factory{
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val creator = viewModels[modelClass]
//            ?: viewModels.asIterable().firstOrNull() { modelClass.isAssignableFrom(it.key)}?.value
//            ?: throw java.lang.IllegalArgumentException("unknown model class $modelClass")
//        return try {
//            creator.get() as T
//        } catch (e: Exception) {
//            throw RuntimeException(e)
//        }
//    }
//
//
//}
