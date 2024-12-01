<html>
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpRequestAttachment" -->
<head>
    <meta http-equiv="content-type" content="text/html; charset = UTF-8">
    <script src="https://yastatic.net/jquery/2.2.3/jquery.min.js" crossorigin="anonymous"></script>

    <link href="https://yastatic.net/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://yastatic.net/bootstrap/3.3.6/js/bootstrap.min.js" crossorigin="anonymous"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>
    <script src="pretty-print-json.js"></script>

    <style>
        pre {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
<#if data.body??><div><pre>
<code>${data.url}</code>
<code>${data.body}</code></pre></div>
</#if>
</body>
</html>