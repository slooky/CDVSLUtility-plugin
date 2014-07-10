var cdslutility = {
	openExternalURL : function (url, title, message, positiveButtonTitle, negativeButtonTitle, successCallback, errorCallback) {
		cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'CDSLUtility', // mapped to our native Java class called "CDSLUtility"
            'openExternalURL', // with this action name
            [{                  // and this array of custom arguments
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