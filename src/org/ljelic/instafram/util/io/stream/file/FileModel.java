package org.ljelic.instafram.util.io.stream.file;

import org.ljelic.instafram.model.*;
import org.ljelic.instafram.util.Machine;
import org.ljelic.instafram.util.io.stream.Streamable;
import org.ljelic.instafram.view.component.Node;

import java.util.List;

public class FileModel implements FileStreamable<AbstractModel> {

    private static final String PROPERTY_SEPARATOR = "<<ATTR>>";
    private static final String NODE_SEPARATOR = "<<NODE>>";
    private static final String LINE_BREAK = "<<LINE>>";

    private AbstractModel model;

    @Override
    public void read(String line) {
        AbstractModel parent = model;
        AbstractModel model = null;

        String[] nodes = line.split(NODE_SEPARATOR);

        for(String token : nodes) {
            if(token.isEmpty()) {
                continue;
            }

            String[] properties = token.split(PROPERTY_SEPARATOR);

            String type = strip(properties[0], true);
            String name = strip(properties[1], true);
            String content = strip(properties[2], true);

            if(type.equals(Company.class.getSimpleName())) {
                model = new Company(name);
            }else if(type.equals(Product.class.getSimpleName())) {
                model = new Product(name);
            }else if(type.equals(Module.class.getSimpleName())) {
                model = new Module(name);
            }else if(type.contains(Parameter.class.getSimpleName())) {
                Parameter p = new Parameter(name, parent.getChildCount());
                p.setValue(strip(properties[3], true));

                try {
                    p.setValueType(ValueType.valueOf(strip(properties[4], true)));
                }catch(IllegalArgumentException e) {
                    p.setValueType(ValueType.TEXT);
                }

                model = p;
            }else if(type.equals(Root.class.getSimpleName())) {
                continue;
            }

            if(model != null) {
                model.setContent(content);

                for(Node child : parent.getChildren()) {
                    if(child instanceof AbstractModel) {
                        if(child.equals(model)) {
                            model = (AbstractModel) child;
                            break;
                        }
                    }
                }

                parent.addChild(model);
                parent = model;
            }
        }
    }

    @Override
    public String write(StringBuilder builder) {
        StringBuilder propertyBuilder = new StringBuilder();
        propertyBuilder.append(NODE_SEPARATOR);

        List<String> properties = model.getProperties();

        for(int i = 0; i < properties.size(); i++) {
            if(i > 0) {
                propertyBuilder.append(PROPERTY_SEPARATOR);
            }

            propertyBuilder.append(strip(properties.get(i), false));
        }

        String current = propertyBuilder.toString();

        builder.append(model.getPrefix());
        builder.append(current);
        builder.append(Machine.lineSeparator());

        for(Node child : model.getChildren()) {
            if(child instanceof AbstractModel) {
                AbstractModel model = (AbstractModel) child;
                model.setPrefix(this.model.getPrefix() + current);

                ((FileModel) new FileModel().from(model)).write(builder);
            }
        }

        return builder.toString();
    }

    @Override
    public Streamable<AbstractModel> from(AbstractModel streamable) {
        model = streamable;

        return this;
    }

    @Override
    public AbstractModel get() {
        return model;
    }

    private String strip(String content, boolean inverse) {
        if(inverse) {
            return content.replace(LINE_BREAK, Machine.lineSeparator());
        }

        return content.replace(Machine.lineSeparator(), LINE_BREAK);
    }
}