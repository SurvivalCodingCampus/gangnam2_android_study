package com.survivalcoding.gangnam2kiandroidstudy.data.datasource

import com.survivalcoding.gangnam2kiandroidstudy.data.model.Procedure

interface ProcedureDataSource {
    fun getAllProcedure(): List<Procedure>
}