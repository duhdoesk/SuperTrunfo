package com.duhdoesk.supertrunfoclone.ui.inGame

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import coil.request.ErrorResult
import coil.request.ImageRequest
import com.duhdoesk.supertrunfoclone.R
import com.duhdoesk.supertrunfoclone.databinding.FragmentInGameBinding
import com.duhdoesk.supertrunfoclone.datasource.Datasource
import com.duhdoesk.supertrunfoclone.model.Card
import com.duhdoesk.supertrunfoclone.ui.inGame.inGameHelper.Deck
import com.google.android.material.snackbar.Snackbar

class InGameFragment : Fragment() {

    private var _binding: FragmentInGameBinding? = null
    private val binding get() = _binding!!
    private val args: InGameFragmentArgs by navArgs()

    private var deck: Deck? = null
    private var myCards: MutableList<Card>? = null
    private var oppCards: MutableList<Card>? = null
    private var myCard: Card? = null
    private var oppCard: Card? = null

    private var myDeckSize: Int = 0
    private var oppDeckSize: Int = 0

    private var buttonST: Button? = null

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInGameBinding.inflate(inflater, container, false)
        return binding.apply {

            buttonST = binding.btSuperTrunfo
            deck = Datasource.getDeck(requireContext(), args.collection.toInt())
            myCards = Datasource.splitCards(deck!!, "me")?.toMutableList()
            oppCards = Datasource.splitCards(deck!!, "opp")?.toMutableList()
            myDeckSize = myCards?.size!!
            oppDeckSize = oppCards?.size!!

            refreshViews()

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCall: Button = binding.buttonCall
        var winner: String = ""

        (activity as AppCompatActivity).supportActionBar?.title = "Super Trunfo ${deck?.name}"

        buttonCall.setOnClickListener(View.OnClickListener {
            if (binding.radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(context, "Please, select an attribute to call", Toast.LENGTH_SHORT).show()
            } else {
                winner = when (resources.getResourceEntryName(binding.radioGroup.checkedRadioButtonId)) {
                    "radioOption1" -> Datasource.cardBattle(myCard!!.att1, oppCard!!.att1)
                    "radioOption2" -> Datasource.cardBattle(myCard!!.att2, oppCard!!.att2)
                    "radioOption3" -> Datasource.cardBattle(myCard!!.att3, oppCard!!.att3)
                    "radioOption4" -> Datasource.cardBattle(myCard!!.att4, oppCard!!.att4)
                    else -> ""
                }

                passTheCard(winner)
                checkGameState()
            }
        })

        buttonST?.setOnClickListener(View.OnClickListener {
            if (Datasource.superTrunfo(oppCard!!.id)) passTheCard("CPU") else passTheCard("Player")
            Snackbar.make(view, "CPU card id: ${oppCard!!.id}", Snackbar.LENGTH_SHORT).show()
            checkGameState()
        })
    }

    private fun checkGameState() {
        myDeckSize = myCards?.size!!
        oppDeckSize = oppCards?.size!!

        when {
            oppDeckSize == 0 -> findNavController().navigate(InGameFragmentDirections.actionDestinationInGameToDestinationGameWon())
            myDeckSize == 0 -> findNavController().navigate(InGameFragmentDirections.actionDestinationInGameToDestinationGameOver())
            else -> refreshViews()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun refreshViews() {

        binding.tvYourCardsNumber.text = "$myDeckSize"
        binding.tvOppCardsNumber.text = "$oppDeckSize"

        myCard = myCards?.get(0)
        oppCard = oppCards?.get(0)

        binding.ivCardArt.load(myCard!!.img) {
            listener(onError = { request: ImageRequest, result: ErrorResult ->
                binding.ivCardArt.setImageResource(R.drawable.ic_launcher_background)
            })
        }

        binding.tvCardName.text = myCard?.name
        binding.tvCardId.text = myCard?.id

        binding.radioOption1.text = "${deck?.att1Label}"
        binding.radioOption2.text = "${deck?.att2Label}"
        binding.radioOption3.text = "${deck?.att3Label}"
        binding.radioOption4.text = "${deck?.att4Label}"

        binding.tvOption1.text = "${myCard?.att1.toString()} ${deck?.att1Unit}"
        binding.tvOption2.text = "${myCard?.att2.toString()} ${deck?.att2Unit}"
        binding.tvOption3.text = "${myCard?.att3.toString()} ${deck?.att3Unit}"
        binding.tvOption4.text = "${myCard?.att4.toString()} ${deck?.att4Unit}"

        binding.radioGroup.clearCheck()

        if (myCard?.trunfo!!) buttonST!!.visibility = View.VISIBLE else buttonST!!.visibility = View.GONE
    }

    private fun passTheCard(winner: String) {
        when (winner) {
            "Player" -> {
                myCards!!.add(oppCard!!)
                myCards!!.add(myCard!!)
                myCards!!.removeFirst()
                oppCards!!.removeFirst()
            }
            "CPU" -> {
                oppCards!!.add(myCard!!)
                oppCards!!.add(oppCard!!)
                oppCards!!.removeFirst()
                myCards!!.removeFirst()

                val myDeckSize = myCards!!.size - 1
                val rd = (0..myDeckSize).random()
                for (i in 0..rd) {
                    oppCards!!.add(myCard!!)
                    oppCards!!.add(oppCard!!)
                    oppCards!!.removeFirst()
                    myCards!!.removeFirst()
                }

                if (myCards!!.size > 0) {
                    myCards!!.add(oppCard!!)
                    myCards!!.add(myCard!!)
                    myCards!!.removeFirst()
                    oppCards!!.removeFirst()
                }

                Snackbar.make(requireView(), "O oponente venceu ${rd + 2} duelo(s)", Snackbar.LENGTH_LONG).show()
            }
            else -> {
                Toast.makeText(context, "Please, select an attribute to call", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
