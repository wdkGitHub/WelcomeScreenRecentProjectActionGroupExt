package dekun.wang;

import com.intellij.openapi.actionSystem.ActionUpdateThread;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import dekun.wang.utils.CommandOrApp;
import dekun.wang.utils.CommandUtils;
import dekun.wang.utils.ExecuteCommand;
import dekun.wang.utils.ProjectInfo;
import org.jetbrains.annotations.NotNull;

/**
 * @author WangDeKun
 *
 * <p>
 */
public class GitOpen extends AnAction {
    private static final Logger LOG = Logger.getInstance(GitOpen.class);

    @Override
    public @NotNull ActionUpdateThread getActionUpdateThread() {
        return ActionUpdateThread.BGT;
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        if (CommandUtils.commandNotExists(CommandOrApp.Command.GitOpen_Command)) {
            event.getPresentation().setEnabledAndVisible(false);
        } else {
            event.getPresentation().setEnabledAndVisible(ProjectInfo.isRecentProjectItem(event) && ProjectInfo.isGitRepository(event) && ProjectInfo.hasRemoteRepository(event));
        }
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        ExecuteCommand.execute(ProjectInfo.getProjectPath(event), CommandOrApp.Command.GitOpen_Command);
    }
}
