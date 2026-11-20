package com.example.data.model

import com.example.data.model.quiz.quizCardioQuestions
import com.example.data.model.quiz.quizDermatologyQuestions
import com.example.data.model.quiz.quizDigestiveQuestions
import com.example.data.model.quiz.quizGeneralQuestions
import com.example.data.model.quiz.quizNeuroQuestions
import com.example.data.model.quiz.quizRespQuestions
import com.example.data.model.quiz.quizRheumatologyQuestions
import com.example.data.model.quiz.quizUrinaryQuestions
import com.example.data.model.sheets.SemiologyCardioExpansion
import com.example.data.model.sheets.SemiologyCardioSheets
import com.example.data.model.sheets.SemiologyDermatologyExpansion
import com.example.data.model.sheets.SemiologyDermatologyExpansion2
import com.example.data.model.sheets.SemiologyDermatologySheets
import com.example.data.model.sheets.SemiologyDigestiveExpansion
import com.example.data.model.sheets.SemiologyDigestiveSheets
import com.example.data.model.sheets.SemiologyEndocrinoSheets
import com.example.data.model.sheets.SemiologyEndocrinoSheets2
import com.example.data.model.sheets.SemiologyEntSheets1
import com.example.data.model.sheets.SemiologyEntSheets2
import com.example.data.model.sheets.SemiologyGeneralSheets
import com.example.data.model.sheets.SemiologyGynecoObstSheets1
import com.example.data.model.sheets.SemiologyGynecoObstSheets2
import com.example.data.model.sheets.SemiologyHematoSheets1
import com.example.data.model.sheets.SemiologyHematoSheets2
import com.example.data.model.sheets.SemiologyInfectiousSheets1
import com.example.data.model.sheets.SemiologyInfectiousSheets2
import com.example.data.model.sheets.SemiologyNeuroExpansion
import com.example.data.model.sheets.SemiologyNeuroSheets
import com.example.data.model.sheets.SemiologyOphthalmoSheets1
import com.example.data.model.sheets.SemiologyOphthalmoSheets2
import com.example.data.model.sheets.SemiologyRespExpansion
import com.example.data.model.sheets.SemiologyRespSheets
import com.example.data.model.sheets.SemiologyRheumatologyExpansion
import com.example.data.model.sheets.SemiologyRheumatologySheets
import com.example.data.model.sheets.SemiologyUrinaryExpansion
import com.example.data.model.sheets.SemiologyUrinaryExpansion2
import com.example.data.model.sheets.SemiologyUrinarySheets

object SemiologyDataProvider {

  val sheets: List<SemiologySheet> by lazy {
    SemiologyCardioSheets.sheets +
      SemiologyCardioExpansion.sheets +
      SemiologyRespSheets.sheets +
      SemiologyRespExpansion.sheets +
      SemiologyDigestiveSheets.sheets +
      SemiologyDigestiveExpansion.sheets +
      SemiologyNeuroSheets.sheets +
      SemiologyNeuroExpansion.sheets +
      SemiologyUrinarySheets.sheets +
      SemiologyUrinaryExpansion.sheets +
      SemiologyUrinaryExpansion2.sheets +
      SemiologyEndocrinoSheets.sheets +
      SemiologyEndocrinoSheets2.sheets +
      SemiologyRheumatologySheets.sheets +
      SemiologyRheumatologyExpansion.sheets +
      SemiologyDermatologySheets.sheets +
      SemiologyDermatologyExpansion.sheets +
      SemiologyDermatologyExpansion2.sheets +
      SemiologyHematoSheets1.sheets +
      SemiologyHematoSheets2.sheets +
      SemiologyInfectiousSheets1.sheets +
      SemiologyInfectiousSheets2.sheets +
      SemiologyEntSheets1.sheets +
      SemiologyEntSheets2.sheets +
      SemiologyOphthalmoSheets1.sheets +
      SemiologyOphthalmoSheets2.sheets +
      SemiologyGynecoObstSheets1.sheets +
      SemiologyGynecoObstSheets2.sheets +
      SemiologyGeneralSheets.sheets
  }

  val quizQuestions: List<QuizQuestion> by lazy {
    quizCardioQuestions +
      quizRespQuestions +
      quizDigestiveQuestions +
      quizNeuroQuestions +
      quizUrinaryQuestions +
      quizRheumatologyQuestions +
      quizDermatologyQuestions +
      quizGeneralQuestions
  }
}
