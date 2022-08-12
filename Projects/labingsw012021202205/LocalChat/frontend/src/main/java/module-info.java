module ch.supsi.localchat.frontend {
    requires javafx.controls;
    requires javafx.fxml;
    requires backend;
    requires org.json;
    opens ch.supsi.localchat.frontend to javafx.fxml;
    exports ch.supsi.localchat.frontend;
    exports ch.supsi.localchat.frontend.model;
    opens ch.supsi.localchat.frontend.model to javafx.fxml;
}
