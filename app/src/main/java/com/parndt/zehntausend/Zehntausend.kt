package com.parndt.zehntausend

import android.app.Application
import com.parndt.zehntausend.data.NoDataException
import com.parndt.zehntausend.data.ScoreLoader
import com.parndt.zehntausend.model.GameState

class Zehntausend : Application() {

    companion object {
        lateinit var gameState: GameState
    }

    override fun onCreate() {
        super.onCreate()

        try {
            gameState = ScoreLoader().load(applicationContext)

            if (gameState.gameOver()) {
                throw NoDataException("No unfinished game in storage. ")
            }
        }
        catch (e: NoDataException) {
            gameState = GameState()
        }
    }
}