/*
 * Copyright (C) 2011-2013 Chris Brody
 * Copyright (C) 2011 Davide Bertola
 *
 * This library is available under the terms of the MIT License (2008).
 * See http://opensource.org/licenses/alphabetical for full text.
 */

#import <Foundation/Foundation.h>

#import <Cordova/CDVPlugin.h>
#import <Cordova/CDVJSON.h>


@interface CDSLUtility : CDVPlugin

- (void)openExternalURL:(NSMutableArray*)arguments withDict:(NSMutableDictionary*)options;

@end
