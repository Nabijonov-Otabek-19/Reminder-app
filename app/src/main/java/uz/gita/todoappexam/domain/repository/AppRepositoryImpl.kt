package uz.gita.todoappexam.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import uz.gita.todoappexam.data.common.TodoData
import uz.gita.todoappexam.data.source.local.dao.TodoDao
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor(
    private val dao: TodoDao
) : AppRepository {

    override fun add(todoData: TodoData) {
        dao.add(todoData.toEntity())
    }

    override fun delete(todoData: TodoData) {
        dao.delete(todoData.toEntity())
    }

    override fun update(todoData: TodoData) {
        dao.update(todoData.toEntity())
    }

    override fun retrieveAllContacts(): Flow<List<TodoData>> =
        dao.retrieveAllContacts().map { list ->
            list.map { it.toData() }
        }

    override fun updateCompletion(state: Boolean, todoId: Long) {
        dao.updateCompletion(state, todoId)
    }
}