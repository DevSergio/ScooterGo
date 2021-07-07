package net.scootergo.app.ui.home

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import net.scootergo.app.R
import net.scootergo.app.ui.MainActivity
import net.scootergo.app.utils.extensions.affectOnItemClicks
import net.scootergo.app.utils.extensions.isPackageInstalled
import net.scootergo.app.utils.extensions.setDivider
import timber.log.Timber


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    lateinit var list: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun getData() {
        viewModel.responseLiveData.observe(viewLifecycleOwner, {
            for (scooter in it.products!!) {
                Timber.d(scooter.name)

                list.setDivider(R.drawable.recycler_view_divider)

                list.layoutManager = LinearLayoutManager(context)
                list.adapter = HomeAdapter(it.products!!)

                list.affectOnItemClicks { position, _ ->
                    launchApp(it.products!![position].playPackage.toString())
                }
            }
        })

        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun launchApp(appPackageName: String) {
        if (context?.isPackageInstalled(appPackageName)!!) {
            val intent: Intent =
                context?.packageManager?.getLaunchIntentForPackage(appPackageName)!!
            startActivity(intent)
        } else {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=$appPackageName")
                    )
                )
            } catch (exception: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
                    )
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as MainActivity).supportActionBar?.title = getString(R.string.home_fragment_title)

        list = view.findViewById(R.id.list)
        progressBar = view.findViewById(R.id.progressBar)

        getData()
    }
}