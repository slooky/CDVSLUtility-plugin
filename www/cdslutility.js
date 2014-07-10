var cdslutility = {
	openExternalURL : function (url, title, message, positiveButtonTitle, negativeButtonTitle, successCallback, errorCallback) {
		cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'Utility', // mapped to our native Java class called "Calendar"
            'openExternalURL', // with this action name
            [{                  // and this array of custom arguments to create our entry
                "url": url,
                "title": title,
                "message": message,
                "positiveButtonTitle": positiveButtonTitle,
                "negativeButtonTitle": negativeButtonTitle
            }]
        );
	}
}
module.exports = cdslutility;