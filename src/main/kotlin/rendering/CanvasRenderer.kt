package rendering

import java.awt.*
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent

class CanvasRenderer(val canvas: Canvas) {

    private val mainFrame: Frame = Frame("Geometry")
    private val controlPanel: Panel = Panel()

    init {
        prepareGUI()
    }

    private fun prepareGUI() {
        mainFrame.setSize(1600, 2000)
        mainFrame.layout = GridLayout(1, 1)
        mainFrame.addWindowListener(object : WindowAdapter() {
            override fun windowClosing(windowEvent: WindowEvent?) {
                System.exit(0)
            }
        })
        controlPanel.layout = FlowLayout()
        controlPanel.add(canvas)

        mainFrame.add(controlPanel)
        mainFrame.isVisible = true
    }
}