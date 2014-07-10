/*
 * Copyright (C) 2011-2013 Chris Brody
 * Copyright (C) 2011 Davide Bertola
 *
 * This library is available under the terms of the MIT License (2008).
 * See http://opensource.org/licenses/alphabetical for full text.
 */

#import "CDVSLUtility.h"
#include <regex.h>

static unsigned int kALERT_TAG_CONFIRM = 999;

@interface CDVSLUtility (Private)



@end

@implementation CDVSLUtility

- (void)openExternalURL:(CDVInvokedUrlCommand*)command {
    //get the callback id
    self.callbackId = command.callbackId;
    
    
    NSLog(@"CDVSLUtility - openExternalURL %@", command.arguments);
    
    NSString *url = [command argumentAtIndex:0];
    
    if (url == nil && url.length <= 0) {
        [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_IO_EXCEPTION messageAsString:@"URL cannot be null!"] callbackId:self.callbackId];
    }
    else {
        self.urlToOpen = [NSURL URLWithString:url];
        NSString *title = [command argumentAtIndex:1 withDefault:@"Title"];
        NSString *message = [command argumentAtIndex:2 withDefault:@"Message?"];
        NSString *positieveButtonTitle = [command argumentAtIndex:3 withDefault:@"Yes"];
        NSString *negativeButtonTitle = [command argumentAtIndex:4 withDefault:@"No"];
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:title
                                                        message:message
                                                       delegate:self
                                              cancelButtonTitle:negativeButtonTitle
                                              otherButtonTitles:positieveButtonTitle, nil];
        alert.tag = kALERT_TAG_CONFIRM;
        [alert show];
    }
}

#pragma mark - UIAlertViewDelegate method

- (void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex {
    if (alertView.tag == kALERT_TAG_CONFIRM) {
        if (buttonIndex == 0) {
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR] callbackId:self.callbackId];
        }
        else {
            [self.commandDelegate sendPluginResult:[CDVPluginResult resultWithStatus:CDVCommandStatus_OK] callbackId:self.callbackId];
            [[UIApplication sharedApplication] openURL:self.urlToOpen];
        }
    }
}

@end
