package com.duhdoesk.supertrunfoclone.inGame

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.duhdoesk.supertrunfoclone.databinding.FragmentInGameBinding
import com.duhdoesk.supertrunfoclone.datasource.Datasource
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Baralho
import com.duhdoesk.supertrunfoclone.inGame.inGameHelper.Carta
import com.google.android.material.snackbar.Snackbar

class InGameFragment : Fragment() {

    private var _binding: FragmentInGameBinding? = null
    private val binding get() = _binding!!
    private val args: InGameFragmentArgs by navArgs()

    private var baralho: Baralho? = null
    private var myCards: MutableList<Carta>? = null
    private var oppCards: MutableList<Carta>? = null
    private var myCard: Carta? = null
    private var oppCard: Carta? = null

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
            baralho = Datasource.getDeck(args.collection.toInt())
            myCards = Datasource.splitCards(baralho!!, "me")?.toMutableList()
            oppCards = Datasource.splitCards(baralho!!, "opp")?.toMutableList()
            myDeckSize = myCards?.size!!
            oppDeckSize = oppCards?.size!!

            refreshViews()

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonCall: Button = binding.buttonCall
        var winner: String = ""

        buttonCall.setOnClickListener(View.OnClickListener {
            if (binding.radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(context, "Please, select an attribute to call", Toast.LENGTH_SHORT).show()
            } else {
                winner = when (resources.getResourceEntryName(binding.radioGroup.checkedRadioButtonId)) {
                    "radioOption1" -> Datasource.cardBattle(myCard!!.op1, oppCard!!.op1)
                    "radioOption2" -> Datasource.cardBattle(myCard!!.op2, oppCard!!.op2)
                    "radioOption3" -> Datasource.cardBattle(myCard!!.op3, oppCard!!.op3)
                    "radioOption4" -> Datasource.cardBattle(myCard!!.op4, oppCard!!.op4)
                    else -> ""
                }

                passTheCard(winner)
                checkGameState()
            }
        })

        buttonST?.setOnClickListener(View.OnClickListener {
            if (Datasource.superTrunfo(oppCard!!.cardId)) passTheCard("CPU") else passTheCard("Player")
            Snackbar.make(view, "CPU card id: ${oppCard!!.cardId}", Snackbar.LENGTH_SHORT).show()
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

        binding.tvCardName.text = myCard?.nome
        binding.tvCardId.text = myCard?.cardId

        binding.radioOption1.text = "${baralho?.op1Text}: ${myCard?.op1.toString()} ${baralho?.op1Unit}"
        binding.radioOption2.text = "${baralho?.op2Text}: ${myCard?.op2.toString()} ${baralho?.op2Unit}"
        binding.radioOption3.text = "${baralho?.op3Text}: ${myCard?.op3.toString()} ${baralho?.op3Unit}"
        binding.radioOption4.text = "${baralho?.op4Text}: ${myCard?.op4.toString()} ${baralho?.op4Unit}"

        binding.radioGroup.clearCheck()

        if (myCard?.cardId == "A1") buttonST!!.visibility = View.VISIBLE else buttonST!!.visibility = View.GONE
    }

    private fun passTheCard(winner: String) {
        when (winner) {
            "Player" -> {
                Toast.makeText(context, "You got it!", Toast.LENGTH_SHORT).show()
                myCards?.add(oppCard!!)
                myCards?.add(myCard!!)
                myCards?.removeFirst()
                oppCards?.removeFirst()
            }
            "CPU" -> {
                Toast.makeText(context, "Loooooooser", Toast.LENGTH_SHORT).show()
                oppCards?.add(oppCard!!)
                oppCards?.add(myCard!!)
                oppCards?.removeFirst()
                myCards?.removeFirst()
            }
            else -> {
                Toast.makeText(context, "Please, select an attribute to call", Toast.LENGTH_SHORT).show()
            }
        }
    }
}