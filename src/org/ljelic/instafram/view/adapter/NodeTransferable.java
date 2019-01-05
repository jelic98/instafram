package org.ljelic.instafram.view.adapter;

import org.ljelic.instafram.util.log.Log;
import org.ljelic.instafram.view.component.Node;
import java.awt.datatransfer.*;
import java.io.IOException;
import java.util.List;

public class NodeTransferable implements Transferable, ClipboardOwner {

    public static DataFlavor flavor;

    private DataFlavor[] supportedFlavors;
    private List<Node> nodes;

    public NodeTransferable(List<Node> nodes) {
        this.nodes = nodes;

        supportedFlavors = new DataFlavor[] {
                flavor = new DataFlavor(this.nodes.getClass(), "Nodes")
        };
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if(!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }

        if(nodes.isEmpty()) {
            throw new IOException(flavor.getClass().getSimpleName() + " flavor not supported");
        }

        return nodes;
    }

    public DataFlavor[] getTransferDataFlavors() {
        return supportedFlavors;
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(this.flavor);
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        Log.d("Violated ISP!");
    }
}