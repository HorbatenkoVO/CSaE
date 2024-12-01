<html>
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpResponseAttachment" -->
<head>
    <meta http-equiv="content-type" content="text/html; charset = UTF-8">
    <script src="jquery.js" crossorigin="anonymous"></script>

    <link href="bootstrap.css" rel="stylesheet" crossorigin="anonymous">
    <script src="bootstrap.js" crossorigin="anonymous"></script>

    <link type="text/css" href="github.css" rel="stylesheet"/>
    <script type="text/javascript" src="highlight.min.js"></script>
    <script type="text/javascript" src="bash.min.js"></script>
    <script type="text/javascript" src="json.min.js"></script>
    <script type="text/javascript" src="xml.min.js"></script>
    <script type="text/javascript">hljs.initHighlightingOnLoad();</script>

    <style>
        pre {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
    <#if data.body??>
        <div><pre><code>${data.body}</code></pre></div>
    </#if>
</body>
</html>