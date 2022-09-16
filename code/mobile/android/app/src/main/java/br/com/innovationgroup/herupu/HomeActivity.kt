package br.com.innovationgroup.herupu

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.LinearLayout
import android.widget.TextView
import br.com.innovationgroup.herupu.model.FeedbackAtividade
import br.com.innovationgroup.herupu.network.FeedbackApi
import br.com.innovationgroup.herupu.network.RetrofitHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeActivity : AppCompatActivity() {
    private lateinit var listaFeedbacks: MutableList<FeedbackAtividade>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        carregarDados()
        setContentView(R.layout.activity_home)

        val btnsNames = ArrayList<String>();
        btnsNames.add("Atividade 1");
        btnsNames.add("Atividade 2");
        btnsNames.add("Atividade 3");
        btnsNames.add("Atividade 4");
        btnsNames.add("Atividade 5");
        btnsNames.add("Atividade 6");

        val gridbtns = findViewById<GridLayout>(R.id.grid_atividades);

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        layoutParams.marginEnd =10;
        layoutParams.marginStart =10;
        layoutParams.topMargin =10;
        layoutParams.bottomMargin =10;
        layoutParams.height = 300;
        layoutParams.width=300;

        for (i in 0 until btnsNames.size) {
            val newButton = Button(this)
            newButton.setText(btnsNames.get(i))
            newButton.id = i
            newButton.setTextColor(Color.parseColor("#44012c"))
            newButton.setTextSize(13F)
            newButton.setTypeface(null, Typeface.BOLD)
            newButton.setBackgroundColor(Color.parseColor("#fdeefd"))

            newButton.setOnClickListener {
            val i = Intent(this, AtividadeActivity::class.java)
            startActivity(i)
        }
            gridbtns.addView(newButton, layoutParams)
        }

        //val btnQuest1 = findViewById<TextView>(R.id.btn_qst1)
//        val btnQuest2 = findViewById<TextView>(R.id.btn_qst2)
//        val btnQuest3 = findViewById<TextView>(R.id.btn_qst3)
//        val btnQuest4 = findViewById<TextView>(R.id.btn_qst4)
//        val btnQuest5 = findViewById<TextView>(R.id.btn_qst5)
//        val btnQuest6 = findViewById<TextView>(R.id.btn_qst6)

        val btnNotas = findViewById<Button>(R.id.btn_notas)
        val viewNotas = findViewById<View>(R.id.view_notas)
        val btnHistorico = findViewById<Button>(R.id.btn_historico)
        val viewHistorico = findViewById<View>(R.id.view_historico)

//        btnQuest1.setOnClickListener {
//            val i = Intent(this, AtividadeActivity::class.java)
//            startActivity(i)
//        }

//        btnQuest2.setOnClickListener {
//            val i = Intent(this, AtividadeActivity::class.java)
//            startActivity(i)
//        }
//
//        btnQuest3.setOnClickListener {
//            val i = Intent(this, AtividadeActivity::class.java)
//            startActivity(i)
//        }
//
//        btnQuest4.setOnClickListener {
//            val i = Intent(this, AtividadeActivity::class.java)
//            startActivity(i)
//        }
//
//        btnQuest5.setOnClickListener {
//            val i = Intent(this, AtividadeActivity::class.java)
//            startActivity(i)
//        }
//
//        btnQuest6.setOnClickListener {
//            val i = Intent(this, AtividadeActivity::class.java)
//            startActivity(i)
//        }

        btnNotas.setOnClickListener {
            val i = Intent(this, NotasActivity::class.java)
            startActivity(i)
        }

        viewNotas.setOnClickListener {
            val i = Intent(this, NotasActivity::class.java)
            startActivity(i)
        }

        btnHistorico.setOnClickListener {
            val i = Intent(this, HistoricoActivity::class.java)
            startActivity(i)
        }

        viewHistorico.setOnClickListener {
            val i = Intent(this, HistoricoActivity::class.java)
            startActivity(i)
        }

    }

    private fun carregarDados(){
        CoroutineScope(Dispatchers.IO).launch() {
            try {
                val result = RetrofitHelper.getInstance().create(FeedbackApi::class.java).getFeedbacks()
                Log.i("EVENTO_API", "retornoApi: Sucesses: ${result.size} registros recuperados")
                listaFeedbacks = mutableListOf()
                result.forEach{listaFeedbacks.add((it))}

                val btnQuest1 = findViewById<TextView>(R.id.btn_qst1)

                withContext(Dispatchers.Main){
                    btnQuest1.setText("Escreva o nome")
                }
            } catch (e: Exception) {
                Log.i("EVENTO_API", "retornoApi: + ${e.message}")
            }

        }
    }
}