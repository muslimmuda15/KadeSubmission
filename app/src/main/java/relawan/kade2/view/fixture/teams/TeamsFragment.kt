package relawan.kade2.view.fixture.teams


import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import relawan.kade2.R
import relawan.kade2.databinding.FragmentTeamsBinding
import relawan.kade2.repository.Repository

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment() {

    private lateinit var teamsViewModel: TeamsViewModel

    private val repository = Repository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val binding = FragmentTeamsBinding.inflate(inflater)

        // lifeCycleOwner
        binding.lifecycleOwner = this

        // get arguments
        val league = arguments?.let { TeamsFragmentArgs.fromBundle(it).league }
        val viewModelFactory = league?.let { TeamsModelFactory(it, repository) }

        // viewModel
        teamsViewModel = ViewModelProviders.of(this, viewModelFactory).get(TeamsViewModel::class.java)

        // adapter
        val adapter = TeamsAdapter(TeamsAdapter.OnClickListener {

            Toast.makeText(context, "${it.idTeam}", Toast.LENGTH_LONG).show()
        })
        binding.teamsList.adapter = adapter

        // get viewModel and adapter to show list
        teamsViewModel.teams.observe(this, Observer {
            it?.let {
                binding.progressBar.visibility = View.GONE
                adapter.data = it
            }
        })

        // enable menu
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  when(item.itemId) {
            R.id.search_menu -> {
                Toast.makeText(context, "search team", Toast.LENGTH_LONG).show()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }

    }
}