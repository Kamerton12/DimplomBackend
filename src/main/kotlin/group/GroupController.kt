package group

import com.google.gson.Gson
import io.javalin.http.Context
import io.javalin.http.Handler
import utils.getAllParams

object GroupController {
    val fetchAllGroups = Handler { ctx ->
        ctx.json(Main.groupInteractor.getAll(ctx.getAllParams()))
    }

    val getGroup = Handler { ctx ->
        ctx.json(Main.groupInteractor.get(ctx.getParamId()))
    }

    val addGroup = Handler { ctx ->
        val data = Gson().fromJson(ctx.body(), Group::class.java)
        ctx.json(Main.groupInteractor.add(data))
    }

    val editGroup = Handler { ctx ->
        val data = Gson().fromJson(ctx.body(), Group::class.java).copy(id = ctx.getParamId())
        ctx.json(Main.groupInteractor.edit(data))
    }

    val deleteGroup = Handler { ctx ->
        ctx.json(Main.groupInteractor.delete(ctx.getParamId()))
    }

    private fun Context.getParamId(): Int {
        return pathParam(":group-id").toIntOrNull() ?: GROUP_INVALID_ID
    }
}