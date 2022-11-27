package com.example.thelastchallengeadmin.vrgame.QuizGame.RecyclerView

data class QuizGameRecyclerViewDataclasss(
    var question_en :String ="",
    var question_sp :String ="",
    var option1_en  :String ="",
    var option1_sp  :String ="",
    var option2_en  :String ="",
    var option2_sp  :String ="",
    var option3_en  :String ="",
    var option3_sp  :String ="",
    var option4_en  :String ="",
    var option4_sp  :String ="",
    var correct     :Int =0,
    var docID       :String ="",
    var onlinestatus:Boolean =false,
    var adult:Boolean=false
)