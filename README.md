CDVSLUtility-plugin
==================

Implementation
==================

```
CDVSLUtility.openExternalURL(
  "http://slooky.co", //url to open - cannot be null
  "Are you sure?", //alert title
  "Open this link external", //alert message
  "Open", //alert positive button title - default: "Yes"
  "Cancel", //alert negative button title - default: "No"
  function (response) { alert("success") }, //success callback function
  function (response) { alert("error") }, //error callback function
);
```

http://slooky.co
