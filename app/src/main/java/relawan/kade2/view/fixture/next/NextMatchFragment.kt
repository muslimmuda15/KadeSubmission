package relawan.kade2.view.fixture.next


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import relawan.kade2.databinding.FragmentNextMatchBinding

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment : Fragment() {

    private lateinit var nextMatchViewModel: NextMatchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = FragmentNextMatchBinding.inflate(inflater)

        val application = requireNotNull(activity).application

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get arguments
        val league = arguments?.let { NextMatchFragmentArgs.fromBundle(it).league }
        val viewModelFactory = league?.let { NextMatchModelFactory(it, application) }

        // viewModel
        nextMatchViewModel = ViewModelProviders.of(this, viewModelFactory).get(NextMatchViewModel::class.java)

        // adapter
        val adapter = NextMatchAdapter(NextMatchAdapter.OnClickListener{
            // navigate to detailMatchFragment with argument
            val action = NextMatchFragmentDirections.actionNextMatchFragmentToDetailMatchFragment(it, null)
            findNavController().navigate(action)
        })
        binding.leagueNextMatch.adapter = adapter

        // get viewModel and adapter to show list
        nextMatchViewModel.nextMatch.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }
        })


        return binding.root
    }


}
