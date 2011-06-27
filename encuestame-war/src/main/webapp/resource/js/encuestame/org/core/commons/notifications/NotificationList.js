dojo.provide("encuestame.org.core.commons.notifications.NotificationList");

dojo.require('dojox.timing');
dojo.require("dojox.widget.Dialog");
dojo.require("dijit._Templated");
dojo.require("dijit._Widget");
dojo.require("dijit.layout.ContentPane");
dojo.require('encuestame.org.core.commons');

dojo.declare(
    "encuestame.org.core.commons.notifications.NotificationList",
    [dijit._Widget, dijit._Templated],{
        templatePath: dojo.moduleUrl("encuestame.org.core.commons.notifications", "template/notificationList.html"),

        widgetsInTemplate: true
    }
);