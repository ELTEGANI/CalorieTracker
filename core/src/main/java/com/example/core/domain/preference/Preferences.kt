package com.example.core.domain.preference

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo


interface Preferences {
    fun saveGender(gender: Gender)
    fun saveAge(age:Int)
    fun saveWeight(weight:Float)
    fun saveHeight(height:Int)
    fun saveActivityLevel(level: ActivityLevel)
    fun saveGoalType(type: GoalType)
    fun saveCarbRatio(ratio:Float)
    fun saveProteinRatio(ratio: Float)
    fun saveFatRatio(ratio: Float)
    fun loadUserInfo():UserInfo
}