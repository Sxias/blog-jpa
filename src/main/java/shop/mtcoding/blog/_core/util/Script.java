package shop.mtcoding.blog._core.util;

public class Script {
    public static String alert(String msg) {
        String html = """
                <script>
                    alert('${msg}');
                </script>
                """.replace("${msg}", msg);
        return html;
    }


    public static String back(String msg) {
        String html = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}", msg);
        return html;
    }

    public static String href(String msg, String location) {
        String html = """
                <script>
                    alert('${msg}');
                    location.href = '${location}';
                </script>
                """.replace("${msg}", msg)
                .replace("${location}", location);
        return html;
    }


}