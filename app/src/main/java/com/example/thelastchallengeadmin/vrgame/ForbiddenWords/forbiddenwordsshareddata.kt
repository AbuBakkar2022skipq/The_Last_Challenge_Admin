package com.example.thelastchallengeadmin.vrgame.ForbiddenWords

import com.example.thelastchallengeadmin.vrgame.Mimicry.EditGame.SelectResources.RecyclerView.dataclassrecyclerViewwordss
import com.example.thelastchallengeadmin.vrgame.Mimicry.Words.RecyclerView.recyclerViewDataModel
import java.util.ArrayList

object forbiddenwordsshareddata {

    var resName_eng2:String=""
    var resName_sp2:String=""
    var docID2:String=""
    var onlinestatus2:Boolean=false
    var adult2:Boolean=false
    var modelsdata = ArrayList<recyclerViewDataModel>()
    var selectwordsdata = ArrayList<dataclassrecyclerViewwordss>()
    var selectedwordss = ArrayList<dataclassrecyclerViewwordss>()
    var selectedposition=0
    var reload=false

}