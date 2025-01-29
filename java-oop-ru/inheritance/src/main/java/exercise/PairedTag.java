package exercise;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private final String tagBody;
    private final List<Tag> singleTags;

    public PairedTag(String name, Map<String, String> attributes, String tagBody, List<Tag> singleTags) {
        super(name, attributes);
        this.tagBody = tagBody;
        this.singleTags = singleTags;
    }

    public String getTagBody() {
        return tagBody;
    }

    public List<Tag> getSingleTags() {
        return singleTags;
    }

    public String toString() {
        String attributes = getAttributes().entrySet()
                .stream()
                .map(a -> a.getKey() + "=\"" + a.getValue() + "\"")
                .collect(Collectors.joining(" "));

        String singleTags = getSingleTags()
                .stream()
                .map(t -> (SingleTag) t)
                .map(SingleTag::toString)
                .collect(Collectors.joining());

        if (!singleTags.isEmpty() && !getTagBody().isEmpty()) {
            return String.format("<%s %s>%s</%s>%s", getName(), attributes, getTagBody(), getName(), singleTags);
        } else if (singleTags.isEmpty() && !getTagBody().isEmpty()) {
            return String.format("<%s %s>%s</%s>", getName(), attributes, getTagBody(), getName());
        } else if (!singleTags.isEmpty() && getTagBody().isEmpty()) {
            return String.format("<%s %s>%s</%s>", getName(), attributes, singleTags, getName());
        } else if (singleTags.isEmpty() && getTagBody().isEmpty()) {
            return String.format("<%s></%s>", getName(), getName());
        }
        return null;
    }
}
// END
