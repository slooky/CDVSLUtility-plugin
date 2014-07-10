cordova.define("co.slooky.plugins.cdvslutility.CDVSLUtility", function(require, exports, module) { /*
 *
 * Copyright 2014 SLOOKY LLC
 * http://slooky.co
 *
*/

var cdvslutility = {
	openExternalURL : function (url, title, message, positiveButtonTitle, negativeButtonTitle, successCallback, errorCallback) {
		cordova.exec(
            successCallback, // success callback function
            errorCallback, // error callback function
            'CDVSLUtility', // mapped to our native Java class called "CDSLUtility"
            'openExternalURL', // with this action name
            [                 // and this object of custom arguments
                "url": url,
                "title": title,
                "message": message,
                "positiveButtonTitle": positiveButtonTitle,
                "negativeButtonTitle": negativeButtonTitle
            ]
        );
	}
}
module.exports = cdvslutility;
});