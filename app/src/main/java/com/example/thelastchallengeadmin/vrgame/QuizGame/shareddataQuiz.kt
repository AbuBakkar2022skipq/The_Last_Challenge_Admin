package com.example.thelastchallengeadmin.vrgame.QuizGame

import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView.dataclassrecyclerViewwordss
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.recyclerViewDataModel
import com.example.thelastchallengeadmin.vrgame.QuizGame.RecyclerView.QuizGameRecyclerViewDataclasss
import java.util.ArrayList

object shareddataQuiz {
    var resName_eng2:String=""
    var resName_sp2:String=""
    var docID2:String=""
    var onlinestatus2:Boolean=false
    var adult2:Boolean=false
    var modelsdataa = ArrayList<QuizGameRecyclerViewDataclasss>()
    var selectwordsdata = ArrayList<QuizGameRecyclerViewDataclasss>()
    var selectedwordss = ArrayList<QuizGameRecyclerViewDataclasss>()
    var selectedposition=0
    var reload=false

}