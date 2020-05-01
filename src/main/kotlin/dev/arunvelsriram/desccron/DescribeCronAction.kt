package dev.arunvelsriram.desccron

import com.intellij.codeInsight.hint.HintManager
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.components.service


class DescribeCronAction : MenuItemAction() {
    private val cronDescriptor = service<CronDescriptor>()
    private val hintManager = service<HintManager>()

    override fun actionPerformed(e: AnActionEvent) {
        val editor = e.getData(PlatformDataKeys.EDITOR)
        try {
            val description = cronDescriptor.describe(getSelectedText(editor))
            hintManager.showInformationHint(editor!!, description)
        } catch (e: Exception) {
            hintManager.showErrorHint(editor!!, e.message ?: "Failed to describe cron")
        }
    }
}
